package com.brewhog.android.retrofit_kotlin.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import com.brewhog.android.retrofit_kotlin.api.Controller
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import com.brewhog.android.retrofit_kotlin.repository.AnekdotRepository
import retrofit2.Call
import retrofit2.Response

class AnekdotViewModel(val repository: AnekdotRepository) : ViewModel() {
    var isConnected : ObservableBoolean = ObservableBoolean(false)

    fun showAnekdots() {
        loadAnekdotsFromServer()
        Log.w("AnekdotViewModel","!!!!! showAnekdots is run")
    }

    fun loadAnekdotsFromServer(){
        repository.loadAndPutInDatabase()
        Log.w("AnekdotViewModel","!!!!! loadAnekdotsFromServer is run")
    }

    fun getAllAnekdots() : LiveData<List<Anekdot>>{
        return repository.getAnekdots()
        Log.w("AnekdotViewModel","!!!!! getAllAnekdots is run")
    }
}