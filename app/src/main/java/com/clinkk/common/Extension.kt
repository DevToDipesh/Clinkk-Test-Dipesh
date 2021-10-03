package com.clinkk.common

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.clinkk.R
fun AppCompatEditText.getTextFromEditText() : String{
    return this.text.toString().trim()
}
fun EditText.getTextFromEditText() : String{
    return this.text.toString().trim()
}
fun EditText.isEmpty():Boolean{
    return this.text.toString().trim().isNullOrEmpty()
}
fun String.isEmpty():Boolean{
    return this.isNullOrEmpty()
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius=50f
        start()
    }
}
fun ImageView.loadImage(uri:String?, progressDrawable: CircularProgressDrawable){
    val options= RequestOptions()
        .placeholder(progressDrawable)

    Glide.with(context).setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}

fun ImageView.loadGif(uri:Int?, progressDrawable: CircularProgressDrawable){
    val options= RequestOptions()
        .placeholder(progressDrawable)

    Glide.with(context).setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}

@BindingAdapter("android:imageUrl")
fun loadImage(view : ImageView, url:String?){
    view.loadImage(url, getProgressDrawable(view.context))
}



