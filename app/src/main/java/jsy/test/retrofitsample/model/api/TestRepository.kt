package jsy.test.retrofitsample.model.api

import io.reactivex.Flowable
import jsy.test.retrofitsample.model.data.User
import retrofit2.Response
import retrofit2.http.GET

interface TestRepository {

//    @GET("/service/parking-lot/1/vehicle-locations")
//    fun getVehicleLocation(): Single<Response<String>>
    @GET("/todos")
    fun getDefault(): Flowable<Response<ArrayList<User>>>
}