package com.brewhog.android.retrofit_kotlin.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anekdot (
    val desc : String,
    val elementPureHtml : String,
    val link : String?,
    val name : String,
    val site : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)