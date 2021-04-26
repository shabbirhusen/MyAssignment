package com.example.myassignment.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.myassignment.api.AppApiService
import com.example.myassignment.api.RetrofitAPI
import com.example.myassignment.model.ListMovie
import com.example.myassignment.model.Results
import com.google.gson.JsonElement

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeshboardRepo {

    lateinit var deshboardRepo: DeshboardRepo
    lateinit var api: AppApiService

    fun DeshboardRepo() {
        api = RetrofitAPI.cteateService(AppApiService::class.java)

    }

    fun getInstance(): DeshboardRepo? {

        return deshboardRepo
    }



    fun getList( category: String, key: String, language: String, page: Int): MutableLiveData<Results>  {
        DeshboardRepo()
        val newsData = MutableLiveData<Results>()


        api.getPropertyAsync( key, language, page).enqueue(object :
            Callback<Results> {
            override fun onResponse(
                call: Call<Results>,
                response: Response<Results>
            ) {
                if(response.isSuccessful){
                    newsData.value = response.body()
                }


            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                newsData.value = null
                Log.e("exeption==>", "" + t)
            }
        })
        return newsData
    }




}


