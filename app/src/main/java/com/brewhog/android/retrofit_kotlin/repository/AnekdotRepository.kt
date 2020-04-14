package com.brewhog.android.retrofit_kotlin.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.brewhog.android.retrofit_kotlin.api.Controller
import com.brewhog.android.retrofit_kotlin.database.AnekdotDao
import com.brewhog.android.retrofit_kotlin.database.AnekdotDatabase
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import retrofit2.Call
import retrofit2.Response

class AnekdotRepository(application: Application) {
    private var dao : AnekdotDao

    init {
        val database = AnekdotDatabase.getDatabase(application)!!
        dao = database.getAnekdotDao()
    }

    companion object{
        class addDataAsync(val anekdotDao: AnekdotDao) : AsyncTask<List<Anekdot>,Unit,Unit>(){
            override fun doInBackground(vararg p0: List<Anekdot>?) {
                anekdotDao.addAnekdot(p0[0]!!)
                Log.w("AnekdotRepository","!!!!! addDataAsync is run")

            }
        }

        class getAllDataAsync(val anekdotDao: AnekdotDao) : AsyncTask<Unit,Unit, LiveData<List<Anekdot>>>(){
            override fun doInBackground(vararg p0: Unit?): LiveData<List<Anekdot>> {
                return anekdotDao.getAllAnekdots()
                Log.w("AnekdotRepository","!!!!! getAllDataAsync is run")
            }
        }

        class  deleteAllDataAsync(val anekdotDao: AnekdotDao) : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                anekdotDao.deleteAllAnekdots()
            }

        }
    }

    fun loadAndPutInDatabase(){
        Log.w("AnekdotRepository","!!!!! loadAndPutInDatabase is run")

        Controller().getApiArguments().getAnekdots("new anekdot",10)
            .enqueue(object : retrofit2.Callback<List<Anekdot>> {
                override fun onFailure(call: Call<List<Anekdot>>, t: Throwable) {
                    //Log.e(TAG,"anekdots loading was failed")
                    Log.e("AnekdotRepository","!!!!! onFailure",t)

                }

                override fun onResponse(
                    call: Call<List<Anekdot>>,
                    response: Response<List<Anekdot>>
                ) {
                    deleteAllDataAsync(dao).execute()
                    addDataAsync(dao).execute(response.body())

                    Log.w("AnekdotRepository","!!!!! onResponse, list is: ${response.body()}")

//                    Thread(Runnable {
//                        dao.deleteAllAnekdots()
//                        dao.addAnekdot(response.body()!!)
//                    }).start()
                }
            })
    }

    fun getAnekdots() : LiveData<List<Anekdot>>{
        return getAllDataAsync(dao).execute().get()
    }
}