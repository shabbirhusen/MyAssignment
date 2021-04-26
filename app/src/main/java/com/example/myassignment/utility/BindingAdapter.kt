package com.example.myassignment.utility

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.example.myassignment.IMAGE_BASE_PATH


//Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {

    Glide.with(imageView.context)
        .load("$IMAGE_BASE_PATH$imgUrl")

        .into(imageView)
}

@BindingAdapter("avaURL")
fun bindAva(imageView: ImageView, imgUrl: Uri?) {

    Glide.with(imageView.context)
        .load(imgUrl)
        .circleCrop()

        .into(imageView)
}


//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

@BindingAdapter("vis", "playlists")
fun vis(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.VISIBLE else View.GONE
    if (isNetWorkError) {
        view.visibility = View.VISIBLE
    }
}





