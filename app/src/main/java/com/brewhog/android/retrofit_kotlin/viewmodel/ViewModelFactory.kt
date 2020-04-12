package com.brewhog.android.retrofit_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import java.lang.IllegalArgumentException

class ViewModelFactory(val anekdotAdapter: AnekdotAdapter) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AnekdotViewModel::class.java)){
            AnekdotViewModel(anekdotAdapter) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}