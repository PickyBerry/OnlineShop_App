package com.pickyberry.authorization.domain

import com.pickyberry.base.data.User

interface AuthRepository {

    suspend fun addUser(
        user: User
    )

    suspend fun getByName(
        firstName: String
    ): User?

    suspend fun getByMail(
        email: String
    ): Boolean




}