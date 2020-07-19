package com.example.mvvm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.example.mvvm.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun appCtx(): App {
            return instance!!.applicationContext as App
        }
    }

    private fun init() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }
}