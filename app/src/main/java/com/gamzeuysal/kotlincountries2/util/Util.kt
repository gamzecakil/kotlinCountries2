package com.gamzeuysal.kotlincountries2.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gamzeuysal.kotlincountries2.R

//Extensions
//String sınıfına eklenti olusturalım
/*
fun String.myExtension(myParameter:String){
println(myParameter)
}
 */
fun ImageView.dowloadFromUrl(url:String,progressDrawable: CircularProgressDrawable){
    //Glide
    //Place holder -> internetten image'lar inene kadar ne gösterecegiz.
    val options = RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeholderProgressBar(context: Context) :CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
//Data Binding for imageView
@BindingAdapter("android.dowloadUrl")
fun dowloadImage(view : ImageView,url:String){
    view.dowloadFromUrl(url, placeholderProgressBar(view.context))
}
