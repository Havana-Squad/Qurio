package com.thechance.qurio.presentation.main

import android.app.Application
import com.thechance.qurio.di.AppComponent
import com.thechance.qurio.di.AppModule
import com.thechance.qurio.di.DaggerAppComponent

class QurioApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}