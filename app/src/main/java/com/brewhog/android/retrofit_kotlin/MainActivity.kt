package com.brewhog.android.retrofit_kotlin

import android.app.ActivityManager
import android.content.Context
import android.net.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brewhog.android.retrofit_kotlin.adapters.AnekdotAdapter
import com.brewhog.android.retrofit_kotlin.databinding.ActivityMainBinding
import com.brewhog.android.retrofit_kotlin.repository.AnekdotRepository
import com.brewhog.android.retrofit_kotlin.viewmodel.AnekdotViewModel
import com.brewhog.android.retrofit_kotlin.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = AnekdotAdapter(listOf())
        val repository = AnekdotRepository(application)
        val factory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(AnekdotViewModel::class.java)
        val mainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        viewModel.getAllAnekdots().observe(this, Observer {
            adapter.setAnekdotList(it)
            println("!!!!! OBSERVER GET LIST: $it")
        })
        mainBinding.adapter = adapter
        mainBinding.viewModel = viewModel

        checkConnection(viewModel)
        //checkConnectionDeprecated(mainBinding.button)
    }

    fun checkConnection(viewModel : AnekdotViewModel){
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .build()
        var callback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                viewModel.isConnected.set(true)

                Toast.makeText(baseContext,"Network is available", Toast.LENGTH_LONG).show()
            }

            override fun onLost(network: Network) {
                viewModel.isConnected.set(false)

                Toast.makeText(baseContext,"onLost", Toast.LENGTH_LONG).show()
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                viewModel.isConnected.set(false)

                Toast.makeText(baseContext,"onLosing", Toast.LENGTH_LONG).show()
            }

            override fun onUnavailable() {
                viewModel.isConnected.set(false)

                Toast.makeText(baseContext,"onUnavailable", Toast.LENGTH_LONG).show()
            }
        }

        try {
            manager.unregisterNetworkCallback(callback)
        }catch (e: Exception){
            Log.w("MainActivity","NetworkCallback for Wi-fi was not registered or already unregistered")
        }

        manager.registerNetworkCallback(networkRequest,callback)
    }

    fun checkConnectionDeprecated(view : View){
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        view.isEnabled = isConnected
        Toast.makeText(baseContext, "Connected is $isConnected", Toast.LENGTH_LONG).show()
    }
}
