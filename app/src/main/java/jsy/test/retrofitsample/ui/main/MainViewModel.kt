package jsy.test.retrofitsample.ui.main
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.test.retrofitsample.R
import jsy.test.retrofitsample.model.repository.ResourcesProvider
import jsy.test.retrofitsample.model.repository.TestRepositoryImpl
import jsy.test.retrofitsample.util.network.RetrofitClient
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val testRepositoryImpl: TestRepositoryImpl
) : ViewModel() {

    private val logTag = this.javaClass.simpleName
    private val _mainText = MutableLiveData<String>()
    val mainText: LiveData<String>
        get() = _mainText

    init {
        _mainText.value = "1234"
    }

    fun changeMainText(){

        val retrofitClient = RetrofitClient
        _mainText.value = resourcesProvider.getString(R.string.test_provider)
//        Flowable.just("1")
//            .subscribeOn(Schedulers.io()),)
//        .getRetrofit().getVehicleLocation().subscribeOn(Schedulers.io()
        testRepositoryImpl.getDefault().subscribe({
                 Log.d(logTag, "retrofit it :  ${it.body()}")
             },{
                 Log.d(logTag, "retrofit error : $it")
             }).let {  }


    }
}