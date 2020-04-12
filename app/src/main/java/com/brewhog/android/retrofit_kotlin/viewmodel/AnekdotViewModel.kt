package com.brewhog.android.retrofit_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import com.brewhog.android.retrofit_kotlin.api.Controller
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import retrofit2.Call
import retrofit2.Response

class AnekdotViewModel(val anekdotAdapter: AnekdotAdapter) : ViewModel() {
    var anekdotsList = ArrayList<Anekdot>()

    companion object{
        val TAG = "AnekdotViewModel"
    }

    init {
        loadAnekdotsFromServer()
    }

    fun showAnekdots(){
        anekdotAdapter.setAnekdotList(anekdotsList)
    }

    private fun loadAnekdotsFromServer(){
        Controller().getApiArguments().getAnekdots("new anekdot",10)
            .enqueue(object : retrofit2.Callback<List<Anekdot>> {
                override fun onFailure(call: Call<List<Anekdot>>, t: Throwable) {
                    Log.e(TAG,"anekdots loading was failed")
                }

                override fun onResponse(
                    call: Call<List<Anekdot>>,
                    response: Response<List<Anekdot>>
                ) {
                    anekdotsList.addAll(response.body()!!)
                }

            })
    }
}