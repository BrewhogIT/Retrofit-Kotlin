package com.brewhog.android.retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import com.brewhog.android.retrofit_kotlin.databinding.ActivityMainBinding
import com.brewhog.android.retrofit_kotlin.viewmodel.AnekdotViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = AnekdotAdapter(listOf())
        val viewModel = ViewModelProvider(this).get(AnekdotViewModel::class.java)
        val mainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        viewModel.liveData.observe(this, Observer {
            adapter.setAnekdotList(it)
            print(it)
        })
        mainBinding.adapter = adapter
        mainBinding.viewModel = viewModel

    }
}
