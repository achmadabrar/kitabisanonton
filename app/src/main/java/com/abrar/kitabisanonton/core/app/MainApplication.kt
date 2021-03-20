package com.abrar.kitabisanonton.core.app

import android.app.Application
import androidx.multidex.MultiDex
import com.abrar.kitabisanonton.core.di.AppModule
import com.abrar.kitabisanonton.core.di.DatabaseModule
import com.abrar.kitabisanonton.core.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule, DatabaseModule))
        }

    }
}