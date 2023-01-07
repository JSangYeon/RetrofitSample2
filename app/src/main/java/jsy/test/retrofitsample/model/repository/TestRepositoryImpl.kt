package jsy.test.retrofitsample.model.repository

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import jsy.test.retrofitsample.model.data.User
import jsy.test.retrofitsample.util.network.RetrofitClient
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRepositoryImpl @Inject constructor() {
    private val logTag = javaClass.simpleName

    private val retrofitClient = RetrofitClient

    fun getDefault(): Flowable<Response<ArrayList<User>>> {
        Log.d(logTag, "getVehicleLocation")
        return retrofitClient.getRetrofit().getDefault().subscribeOn(Schedulers.io())
    }
}