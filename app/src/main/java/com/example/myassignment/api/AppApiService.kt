package com.example.myassignment.api

import com.example.myassignment.model.ListMovie
import com.example.myassignment.model.Results
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Shabbir on 26/4/21$.
 */
interface AppApiService {

    @GET("movie/popular")
     fun getPropertyAsync(
        @Query("api_key") key: String = "",
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<Results>



}