package jsy.test.retrofitsample.model.repository

import android.util.Log
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jsy.test.retrofitsample.model.data.User
import jsy.test.retrofitsample.util.network.RetrofitClient
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JPHRepository @Inject constructor() { // JsonPlaceHolderRepository

    fun getDefault(): Flowable<Response<ArrayList<User>>> {
        val retrofitClient = RetrofitClient
        return RetrofitClient.getRetrofit().getDefault().subscribeOn(Schedulers.io())
    }

    fun postJson(user: User): Flowable<Response<Any>> {
        return RetrofitClient.getRetrofit().postJson(user).subscribeOn(Schedulers.io())
    }


}