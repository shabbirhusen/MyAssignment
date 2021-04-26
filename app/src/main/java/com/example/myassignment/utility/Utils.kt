package com.example.myassignment.utility


import android.content.Context
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myassignment.R
import com.google.android.material.snackbar.Snackbar


/**
 *
 * Class for common helper  function
 *
 */
class Utils {

    fun isValidEmailId(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.trim { it <= ' ' }).matches()
    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun showSnackbarLong(message: String?, view: View) {
        val snackbar = message?.let { Snackbar.make(view, it, Snackbar.LENGTH_LONG) }

        snackbar?.view?.setBackgroundResource(R.color.colorPrimaryDark)
        val textView = snackbar?.view?.findViewById(
            com.google.android.material.R.id.snackbar_text
        ) as TextView
        textView.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        textView.gravity = Gravity.START
        snackbar.show()

        snackbar.show()
    }

}


