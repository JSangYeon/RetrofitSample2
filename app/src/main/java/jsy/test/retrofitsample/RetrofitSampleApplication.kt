package jsy.test.retrofitsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RetrofitSampleApplication : Application() {

    companion object {
        lateinit var instance: RetrofitSampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}