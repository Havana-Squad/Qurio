package com.thechance.qurio.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VDB : ViewDataBinding, V: BaseView, P : BasePresenter<V>>
    : Fragment(), BaseView {

    abstract val layoutIdFragment: Int
    protected abstract val presenter: P

    private var _binding: VDB? = null
    protected val binding: VDB
        get() = _binding ?: throw IllegalStateException(
            "Binding is only valid between onCreateView and onDestroyView"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        attachPresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    protected open fun setupViews() {}

    @Suppress("UNCHECKED_CAST")
    private fun attachPresenter() {
        presenter.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }

    protected fun setTitle(visible: Boolean, title: String? = null) {
        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        if (visible) {
            actionBar?.show()
            title?.let { actionBar?.title = it }
        } else {
            actionBar?.hide()
        }
    }
}