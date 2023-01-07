package jsy.test.retrofitsample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jsy.test.retrofitsample.R
import jsy.test.retrofitsample.base.BaseActivity
import jsy.test.retrofitsample.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "startActivity");

        _mainViewModel.mainText.observe(lifecycleOwner) { text ->
                binding.tvMain.text = text
        }

        binding.btnTextChange.setOnClickListener{
            _mainViewModel.changeMainText()
        }

    }

}