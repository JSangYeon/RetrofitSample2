package jsy.test.retrofitsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RetrofitSampleApplication : Application() {

    companion object {
        lateinit var instance: RetrofitSampleApplication

        /**
         * singleton 애플리케이션 객체를 얻는다.
         *
         * @return singleton 애플리케이션 객체
         */
        @JvmStatic
        fun getGlobalApplicationContext(): RetrofitSampleApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }



}