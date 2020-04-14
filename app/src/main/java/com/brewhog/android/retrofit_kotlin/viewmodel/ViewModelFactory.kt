package com.brewhog.android.retrofit_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brewhog.android.retrofit_kotlin.repository.AnekdotRepository
import java.lang.IllegalArgumentException
import java.util.*

class ViewModelFactory(val repository: AnekdotRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AnekdotViewModel::class.java)){
            AnekdotViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}