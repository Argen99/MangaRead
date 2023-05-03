package com.geektech.mangaread.app

import android.app.Application
import com.geektech.mangaread.di.appModule
import com.geektech.mangaread.di.dataModule
import com.geektech.mangaread.di.domainModule
import com.geektech.mangaread.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule, networkModule))
        }
    }
}