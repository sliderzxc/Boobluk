package com.test.boobluk.app

import android.app.Application
import com.test.boobluk.di.component.AppComponent
import com.test.boobluk.di.component.DaggerAppComponent
import com.test.boobluk.di.modules.AppModule
import com.test.boobluk.di.modules.ViewModelModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .viewModelModule(ViewModelModule())
            .appModule(AppModule())
            .build()
    }
}