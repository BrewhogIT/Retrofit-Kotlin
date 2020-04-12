package com.brewhog.android.retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import com.brewhog.android.retrofit_kotlin.databinding.ActivityMainBinding
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import com.brewhog.android.retrofit_kotlin.viewmodel.AnekdotViewModel
import com.brewhog.android.retrofit_kotlin.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = AnekdotAdapter(ArrayList<Anekdot>())
        val factory = ViewModelFactory(adapter)
        val viewModel = ViewModelProvider(this,factory).get(AnekdotViewModel::class.java)
        val mainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        mainBinding.viewModel = viewModel
    }
}
