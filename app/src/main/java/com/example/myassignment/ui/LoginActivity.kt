package com.example.myassignment.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myassignment.App
import com.example.myassignment.R
import com.example.myassignment.dagger.component.ActivityComponent
import com.example.myassignment.dagger.component.DaggerActivityComponent
import com.example.myassignment.databinding.ActivityLoginBinding
import com.example.myassignment.utility.Utils
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Shabbir on 24/4/21$.
 */
open class LoginActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginBinding

    @Inject
    lateinit var mUtils: Utils

    private var component: ActivityComponent? = null

    private val app: App
        get() = applicationContext as App

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerActivityComponent.builder().appComponent(app.appComponent).build()

        component?.inject(this)
        setContentView(R.layout.activity_login)


        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )

        mBinding.btnLogin.isEnabled = false


        Observable.combineLatest(getValidationObservableEmail(mBinding.txtEmail),
            getValidationObservable(mBinding.txtPassword),
            BiFunction<String, String, Boolean> { t1, t2 ->
                return@BiFunction t1.isNotEmpty() && t2.isNotEmpty()
            }).subscribe { b ->
            mBinding.btnLogin.isEnabled = b
        }

        mBinding.btnLogin.setOnClickListener {

            val intent = Intent(
                this,
                MovieListActivity::class.java
            )
            startActivity(intent)
            finish()
        }


    }


    private fun getValidationObservable(edittext: EditText): Observable<String>? {
        return RxTextView.textChanges(edittext).debounce(5, TimeUnit.MILLISECONDS)
            .map { char ->
                if (char.length >= 6) return@map StringBuilder(char).toString() else ""
            }.subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getValidationObservableEmail(edittext: EditText): Observable<String>? {
        return RxTextView.textChanges(edittext).debounce(5, TimeUnit.MILLISECONDS)
            .map { char ->
                if (mUtils.isValidEmailId("" + char)) return@map StringBuilder(char).toString() else ""
            }.subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}





