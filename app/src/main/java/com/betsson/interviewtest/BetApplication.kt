package com.betsson.interviewtest

import android.app.Application
import com.betsson.interviewtest.commom.data.di.dataModule
import com.betsson.interviewtest.commom.domain.di.domainModule
import com.betsson.interviewtest.commom.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BetApplication)
            modules(modules)
        }
    }

    private val modules = listOf(presentationModule, dataModule, domainModule)
}