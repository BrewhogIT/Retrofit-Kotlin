package com.brewhog.android.retrofit_kotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Controller {
    fun getApiArguments() : AnekdotApi{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://umorili.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AnekdotApi::class.java)
    }
}