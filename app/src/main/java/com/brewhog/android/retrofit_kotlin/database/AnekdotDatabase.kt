package com.brewhog.android.retrofit_kotlin.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.brewhog.android.retrofit_kotlin.pojo.Anekdot

@Database(entities = [Anekdot::class],version = 1)
abstract class AnekdotDatabase : RoomDatabase() {
    abstract fun getAnekdotDao() : AnekdotDao

    companion object{
        var database : AnekdotDatabase? = null

        fun getDatabase(application: Application) : AnekdotDatabase? {
            if (database == null){
                database = Room.databaseBuilder(
                    application.applicationContext,
                    AnekdotDatabase::class.java,
                    "anekdot_database").build()
            }

            return  database
        }
    }
}