package com.pickyberry.authorization.data

import com.pickyberry.authorization.domain.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val db: com.pickyberry.base.data.UserDatabase,
) : AuthRepository {

    private val dao = db.dao

    override suspend fun addUser(user: com.pickyberry.base.data.User) {
        dao.addUser(user)
    }

    override suspend fun getByName(firstName: String): com.pickyberry.base.data.User? {
        return dao.getByName(firstName)
    }

    override suspend fun getByMail(email: String): Boolean {
        return dao.getByMail(email)
    }


}