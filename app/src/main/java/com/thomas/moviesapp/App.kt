package com.thomas.moviesapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.thomas.data.di.dataModule
import com.thomas.moviesapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class App : Application() {

    lateinit var appContext: Context
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setAll()
    }

    private fun setAll() {
        setKoin()
        setContext()
        setPreferences()
    }

    private fun setKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(dataModule, appModule))
        }
    }

    private fun setContext() {
        appContext = applicationContext
    }

    private fun setPreferences() {
        preferences = getSharedPreferences("com.thomas.moviesapp.prefs", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

}