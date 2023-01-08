package jsy.test.retrofitsample.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.test.retrofitsample.base.BaseViewModel
import jsy.test.retrofitsample.model.data.User
import jsy.test.retrofitsample.model.repository.ResourcesProvider
import jsy.test.retrofitsample.model.repository.TestRepositoryImpl
import java.util.Observable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepositoryImpl: TestRepositoryImpl
) : BaseViewModel() {

    private val logTag = this.javaClass.simpleName
    private val _mainText = MutableLiveData<String>()
    private val _mainList = MutableLiveData<ArrayList<User>>()

    val mainText: LiveData<String>
        get() = _mainText
    val mainList: LiveData<ArrayList<User>>
        get() = _mainList

    init {
        _mainText.value = "1234"
    }

    fun changeMainText() {

        testRepositoryImpl.getDefault().subscribe({ it ->
            if (it.body() != null) {
                Log.d(logTag, "retrofit userList :  ${it.body()}")
                _mainList.postValue(it.body())

//                val userList = it.body()!!
//                var userTitles = ""
//                userList.forEach {
//                    userTitles += it.title + "\n"
//
//                }
//                _mainText.postValue(userTitles)
            }
        }, {
            Log.d(logTag, "retrofit error : $it")
        }).let { }
    }
}