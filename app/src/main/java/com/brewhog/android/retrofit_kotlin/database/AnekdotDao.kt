package com.brewhog.android.retrofit_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot
import retrofit2.http.DELETE

@Dao
interface AnekdotDao {
    @Query("SELECT * FROM anekdot")
    fun getAllAnekdots(): LiveData<List<Anekdot>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnekdot(anekdotList: List<Anekdot>)

    @Query("DELETE FROM anekdot")
    fun deleteAllAnekdots()

}