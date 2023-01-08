package jsy.test.retrofitsample.util.adapter

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object TestBindingAdapter {
    @BindingAdapter("bindTextDouble")
    @JvmStatic
    fun setText(view: TextView, text: String?){
        if(text==null) return
        Log.d("bindingAdapter","setText : $text")

        view.text = text + "\n" + text
    }

//    @BindingAdapter("imageUrl")
//    @JvmStatic
//    fun loadImage(imageView: ImageView, imageUrl: String){
//        Glide.with(imageView.context)
//            .load(imageUrl)
//            .override(300, 300)
//            .circleCrop()
//            .into(imageView)
//    }
}