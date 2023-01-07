package jsy.test.retrofitsample.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import jsy.test.retrofitsample.model.api.TestRepository
import jsy.test.retrofitsample.util.network.ApiConfig
import jsy.test.retrofitsample.util.network.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object TestRetrofitModule {
    @Singleton
    @Provides
    fun getService() : TestRepository {
        val retrofit = RetrofitClient.retrofit(ApiConfig.API_URL_BASE)
        return retrofit.create(TestRepository::class.java)
    }
}
