package jsy.test.retrofitsample.base

import android.app.Activity
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import jsy.test.retrofitsample.R
import jsy.test.retrofitsample.RetrofitSampleApplication.Companion.getGlobalApplicationContext
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()
    private var backPressedDisposable: Disposable
    protected val backPressedSubject = BehaviorSubject.createDefault(0L) // 생성할 때는 0을 넣는다
    protected val _loading = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    protected var _error = MutableLiveData<String>()

    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<String> get() = _error


    protected val _hideKeyBoardEvent = SingleLiveEvent<Any>()
    protected val _closeEvent = SingleLiveEvent<String>()
    protected val _positiveEvent = SingleLiveEvent<String>()
    protected val _negativeEvent = SingleLiveEvent<String>()


    val hideKeyBoardEvent: LiveData<Any> get() = _hideKeyBoardEvent
    val closeEvent: LiveData<String> get() = _closeEvent
    val positiveEvent: LiveData<String> get() = _positiveEvent
    val negativeEvent: LiveData<String> get() = _negativeEvent


    init {
        backPressedDisposable = backPressedSubject
            .buffer(2, 1) // back 버튼을 한 번 누르면, 이전에 눌렀던 시간과 방금 누른 시간 2개의 값을 발행한다.
            .map {
                Pair<Long, Long>(it.get(0), it.get(1))// 비교하기 쉽게 Pair로 변환
            }
            .map { pair ->
                pair.second - pair.first < TimeUnit.SECONDS.toMillis(2)// 두 번째 누른 시간이 첫 번째 누른 시간보다 2초 이내인가

            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { willFinish ->
                if (willFinish) {
//                        CommonUtil.closeApp()
                    _closeEvent.call()
                } else {
                    Toast.makeText(
                        getGlobalApplicationContext(), getGlobalApplicationContext().getText(
                            R.string.app_close_hint
                        ), Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun finishEvnet() {
        backPressedSubject.onNext(System.currentTimeMillis())
    }

    fun showProgress() {
        _loading.value = true
    }

    fun hideProgress() {
        _loading.value = false
    }


    fun positiveEvent(msg: String?) {
        _positiveEvent.postValue(msg)
    }

    fun negativeEvent(msg: String?) {
        _negativeEvent.postValue(msg)
    }


    var focusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            _hideKeyBoardEvent.call()
        }
    }

    fun createConfirmDialog(
        activity: Activity, title: String = "", msg: String,
        positive: String = getGlobalApplicationContext().getString(R.string.ok),
        negative: String = getGlobalApplicationContext().getString(R.string.cancel),
        gravity: Int = Gravity.LEFT
    ) {
        AlertDialog.Builder(activity).apply {
            setTitle(title)
            setMessage(Html.fromHtml(msg))
            setCancelable(false)
            setPositiveButton(positive) { dialog, which ->
                _positiveEvent.value = msg
            }

            setNegativeButton(negative) { dialog, which ->
                _negativeEvent.value = msg
            }
            create().apply {
                findViewById<TextView>(android.R.id.message)?.gravity = gravity
            }
        }.show()
    }

    fun createAlertDialog(
        activity: Activity, title: String = "", msg: String,
        positive: String = getGlobalApplicationContext().getString(R.string.ok),
        gravity: Int = Gravity.LEFT
    ) {
        AlertDialog.Builder(activity).apply {
            setTitle(title)
            setMessage(Html.fromHtml(msg))
            setCancelable(false)
            setPositiveButton(positive) { dialog, which ->
                _positiveEvent.value = msg
            }
            create().apply {
                findViewById<TextView>(android.R.id.message)?.gravity = gravity
            }
        }.show()
    }
}

