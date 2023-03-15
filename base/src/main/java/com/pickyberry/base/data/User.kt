package com.pickyberry.base.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val email: String,
    val firstName: String,
    val secondName: String,
    @PrimaryKey val id:Int?=null
)