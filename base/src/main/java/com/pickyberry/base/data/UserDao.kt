package com.pickyberry.base.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert()
    suspend fun addUser(
       userEntity: User
    )

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    suspend fun getByName(firstName: String): User?

    @Query("SELECT count() FROM user WHERE email = :email")
    suspend fun getByMail(email: String): Boolean


}