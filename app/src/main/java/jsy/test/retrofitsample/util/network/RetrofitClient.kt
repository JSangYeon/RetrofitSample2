package jsy.test.retrofitsample.util.network

import jsy.test.retrofitsample.model.api.TestRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


    val TYPE_WIFI = 1
    val TYPE_MOBILE = 2
    val TYPE_NOT_CONNECTED = 0
    val NETWORK_STATUS_NOT_CONNECTED = 0
    val NETWORK_STAUS_WIFI = 1
    val NETWORK_STATUS_MOBILE = 2

    fun retrofit(baseUrl: String = ApiConfig.API_URL_BASE): Retrofit {
        val client = getOkHttpClient().build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }



    private fun getOkHttpClient() = OkHttpClient
        .Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//                connectionSpecs(getSSL())
//            }
            addInterceptor(Interceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                return@Interceptor it.proceed(request)
            })
        }
}