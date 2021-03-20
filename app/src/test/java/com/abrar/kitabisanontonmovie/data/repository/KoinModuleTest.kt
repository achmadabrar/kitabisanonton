package com.abrar.kitabisanontonmovie.data.repository

import android.app.Application
import com.abrar.kitabisanonton.core.di.AppModule
import com.abrar.kitabisanonton.core.di.DatabaseModule
import com.abrar.kitabisanonton.core.di.NetworkModule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : AutoCloseKoinTest() {

    @MockK
    private lateinit var context: Application

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.ERROR)
            androidContext(context)
            modules(listOf(AppModule, NetworkModule, DatabaseModule))
        }.checkModules()
    }

}