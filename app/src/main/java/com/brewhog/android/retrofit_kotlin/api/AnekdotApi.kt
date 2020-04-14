package com.brewhog.android.retrofit_kotlin.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnekdotApi {
    @GET("/api/get")
    fun getAnekdots (@Query("name")name : String, @Query("num")num : Int) : Call<List<Anekdot>>
}