package com.example.myassignment.adapter


import android.view.View
import androidx.databinding.BindingAdapter



/**
 * Created by Shabbir on 26/4/21$.
 */

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





