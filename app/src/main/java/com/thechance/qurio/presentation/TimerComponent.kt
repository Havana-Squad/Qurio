package com.thechance.qurio.presentation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import androidx.core.graphics.toColorInt
import com.thechance.qurio.R
import kotlin.math.max
import kotlin.math.min

class TimerComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    var progress: Float = 0f
        set(value) {
            field = value.coerceIn(0f, 1f)
            invalidate()
        }

    var centerText: String? = "100 Sec"
        set(value) {
            field = value
            invalidate()
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SvgProgressView,
            0, 0
        ).apply {
            try {
                progress = getFloat(R.styleable.SvgProgressView_progress, 0f)
                centerText = getString(R.styleable.SvgProgressView_centerText)
            } finally {
                recycle()
            }
        }
    }

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = ContextCompat.getColor(context, R.color.border_color)
        strokeWidth = 3f
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 10f * resources.displayMetrics.density
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
        isFakeBoldText = true

    }

    private val pathDataList = listOf(
        "M 12 13.556 L 16.511 8 L 335.483 8 L 340 13.556 L 318.891 28 L 33.109 28 L 12 13.556 Z",
        "M 335.245 8.5 L 339.269 13.449 L 318.735 27.5 L 33.265 27.5 L 12.729 13.448 L 16.749 8.5 L 335.245 8.5 Z",
        "M 18 13 L 21 10 L 324.926 10 L 328 12.667 L 310 26 L 36.145 26 L 18 13 Z",
        "M 19.758 8 L 15.247 13.556 L 36.356 28 L 33.109 28 L 12 13.556 L 16.511 8 L 19.758 8 Z M 332.242 8 L 336.753 13.556 L 315.644 28 L 318.891 28 L 340 13.556 L 335.489 8 L 332.242 8 Z",
        "M13.6238 20.2222H16.8713L28.2376 28H24.9901L13.6238 20.2222Z",
        "M338.376 20.2222H335.129L323.762 28H327.01L338.376 20.2222Z"
    )

    private val pathColors = listOf(
        ContextCompat.getColor(context, R.color.border_color),
        ContextCompat.getColor(context, R.color.ic_launcher_background),
        ContextCompat.getColor(context, R.color.orange),
        ContextCompat.getColor(context, R.color.orange),
        ContextCompat.getColor(context, R.color.orange50),
        ContextCompat.getColor(context, R.color.orange50),
    )

    private val svgPaths = mutableListOf<Path>()
    private val matrix = Matrix()
    private val originalProgressPoints = mutableListOf<PointF>()

    init {
        for (d in pathDataList) {
            try {
                svgPaths.add(PathParser.createPathFromPathData(d))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private var prepared = false
    private var totalBounds = RectF()
    private val progressBarBounds = RectF()

    private fun extractPathPoints() {
        if (originalProgressPoints.isNotEmpty() || svgPaths.size <= 2) return
        originalProgressPoints.add(PointF(18f, 13f))
        originalProgressPoints.add(PointF(21f, 10f))
        originalProgressPoints.add(PointF(324.926f, 10f))
        originalProgressPoints.add(PointF(328f, 12.667f))
        originalProgressPoints.add(PointF(310f, 26f))
        originalProgressPoints.add(PointF(36.145f, 26f))
    }

    private fun preparePathsIfNeeded(width: Int, height: Int) {
        if (prepared || svgPaths.isEmpty() || width == 0 || height == 0) return

        extractPathPoints()

        val tmp = RectF()
        totalBounds.set(Float.MAX_VALUE, Float.MAX_VALUE, Float.MIN_VALUE, Float.MIN_VALUE)
        for (p in svgPaths) {
            p.computeBounds(tmp, true)
            totalBounds.left = min(totalBounds.left, tmp.left)
            totalBounds.top = min(totalBounds.top, tmp.top)
            totalBounds.right = max(totalBounds.right, tmp.right)
            totalBounds.bottom = max(totalBounds.bottom, tmp.bottom)
        }

        if (totalBounds.left == Float.MAX_VALUE) {
            totalBounds.set(0f, 0f, width.toFloat(), height.toFloat())
        }

        val availableW = width.toFloat() - paddingLeft - paddingRight
        val availableH = height.toFloat() - paddingTop - paddingBottom
        val scale = min(availableW / totalBounds.width(), availableH / totalBounds.height())

        matrix.reset()
        matrix.postScale(scale, scale)
        val dx =
            paddingLeft + (availableW - totalBounds.width() * scale) / 2f - totalBounds.left * scale
        val dy =
            paddingTop + (availableH - totalBounds.height() * scale) / 2f - totalBounds.top * scale
        matrix.postTranslate(dx, dy)

        for (i in svgPaths.indices) {
            val p = Path()
            svgPaths[i].transform(matrix, p)
            svgPaths[i].set(p)
        }

        if (svgPaths.size > 2) {
            svgPaths[2].computeBounds(progressBarBounds, true)
        }

        prepared = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        preparePathsIfNeeded(width, height)

        for (i in svgPaths.indices) {
            val path = svgPaths[i]
            val color = pathColors.getOrElse(i) { Color.WHITE }
            fillPaint.color = color


            if (i == 2) {
                fillPaint.color = "#000000".toColorInt()
                canvas.drawPath(path, fillPaint)
                if (progress > 0f && originalProgressPoints.size >= 6) {
                    fillPaint.color = color

                    val pts = originalProgressPoints
                    val originalWidth = pts[3].x - pts[0].x
                    val targetWidth = originalWidth * progress

                    val dynamicPath = Path()

                    dynamicPath.moveTo(pts[0].x, pts[0].y)

                    dynamicPath.lineTo(pts[1].x, pts[1].y)
                    val newRightX = pts[0].x + targetWidth


                    dynamicPath.lineTo(newRightX - (pts[3].x - pts[2].x), pts[2].y)


                    dynamicPath.lineTo(newRightX, pts[3].y)

                    dynamicPath.lineTo(newRightX - (pts[3].x - pts[4].x), pts[4].y)

                    dynamicPath.lineTo(pts[5].x, pts[5].y)

                    dynamicPath.close()

                    val transformedPath = Path()
                    dynamicPath.transform(matrix, transformedPath)
                    canvas.drawPath(transformedPath, fillPaint)

                }


            } else {
                if (i == 0) {
                    canvas.drawPath(path, strokePaint)
                }
                canvas.drawPath(path, fillPaint)
            }
        }

        centerText?.let {
            val x = width / 2f
            val y = height / 2f - (textPaint.descent() + textPaint.ascent()) / 2f
            canvas.drawText(it, x, y, textPaint)
        }
    }
}