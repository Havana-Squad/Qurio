package com.thechance.qurio.di

import com.thechance.qurio.presentation.main.QurioApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, FragmentModule::class, RepositoryModule::class,
        DataModule::class, ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<QurioApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<QurioApp>
}