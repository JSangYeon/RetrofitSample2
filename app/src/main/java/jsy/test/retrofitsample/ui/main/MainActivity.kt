package jsy.test.retrofitsample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jsy.test.retrofitsample.BR
import jsy.test.retrofitsample.R
import jsy.test.retrofitsample.base.BaseActivity
import jsy.test.retrofitsample.base.BaseRecyclerView
import jsy.test.retrofitsample.databinding.ActivityMainBinding
import jsy.test.retrofitsample.databinding.ItemUserBinding
import jsy.test.retrofitsample.model.data.User

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "startActivity");
        binding.mainViewModel = _mainViewModel

        val adapter = object : BaseRecyclerView.Adapter<User, ItemUserBinding>(
            layoutResId = R.layout.item_user,
            bindingVariableId = BR.user
        ){


        }

//        val userList = ArrayList<User>()
//        val user = User(1,2,"3",false)
//        userList.add(user)

//        adapter.replaceAll(userList)


        binding.rvMain.adapter = adapter
        _mainViewModel.mainList.observe(lifecycleOwner) {
            Log.d(logTag, "it $it")
            adapter.run {
                replaceAll(it)
                notifyDataSetChanged()
            }
        }
//        _mainViewModel.mainText.observe(lifecycleOwner) { text ->
//                binding.tvMain.text = text
//        }

        binding.btnTextChange.setOnClickListener {
            _mainViewModel.changeMainText()
        }

    }

}