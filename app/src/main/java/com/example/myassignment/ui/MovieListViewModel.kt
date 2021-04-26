package com.example.myassignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myassignment.model.ListMovie
import com.example.myassignment.model.Results
import com.google.gson.JsonObject


/**
 * Created by Shabbir on 26/4/21$.
 */
class MovieListViewModel :
    ViewModel() {
     var mutableLiveData: MutableLiveData<Results>? = null

    private var mDeshboardrepo: DeshboardRepo? = null


    fun init(category: String, page: Int) {
        mDeshboardrepo = DeshboardRepo

        mutableLiveData =
            mDeshboardrepo?.getList(category, "d4f57ba5d4a1d9d010da3da15dd94aeb", "en-US", page)

    }

    fun getResponse(): MutableLiveData<Results>? {
        return mutableLiveData
    }


}