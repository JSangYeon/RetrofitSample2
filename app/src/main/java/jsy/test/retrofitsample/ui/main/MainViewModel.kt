package jsy.test.retrofitsample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.test.retrofitsample.R
import jsy.test.retrofitsample.RetrofitSampleApplication
import jsy.test.retrofitsample.model.data.User
import jsy.test.retrofitsample.model.repository.JPHRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jphRepository: JPHRepository
//    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val logTag = this.javaClass.simpleName
    private val _mainText = MutableLiveData<String>()
    val mainText: LiveData<String>
        get() = _mainText

    init {
        _mainText.value = "1234"
    }

    fun changeMainText() {

        _mainText.value =
            RetrofitSampleApplication.instance.applicationContext.getString(R.string.test_provider)


//         jphRepository.getDefault().subscribe({
//                 Log.d(logTag, "retrofit it :  ${it.body()}")
//             },{
//                 Log.d(logTag, "retrofit error : $it")
//             }).let {
//
//         }

        val tempUser = User(
            userId = 19941117,
            id = 55555,
            title = "jsy",
            completed = true
        )
        jphRepository.postJson(tempUser).subscribe({
            Log.d(logTag, "jphRepository postJson complete code : ${it.code()}")
            Log.d(logTag, "jphRepository postJson complete body : ${it.body()}")
        }, {
            Log.d(logTag, "jphRepository postJson error : $it")
        }).let { }


    }
}