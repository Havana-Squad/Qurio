package com.thechance.qurio.presentation.main

import com.thechance.qurio.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class QurioApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}