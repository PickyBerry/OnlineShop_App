package com.pickyberry.authorization.presentation


import androidx.lifecycle.ViewModel
import com.pickyberry.authorization.domain.AuthRepository
import com.pickyberry.base.data.User
import javax.inject.Inject


class AuthorizationViewmodel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    suspend fun addUser(email:String,firstName:String,lastName:String){
        authRepository.addUser(User(email,firstName,lastName))
    }

    suspend fun getByName(firstName:String): User?{
        return authRepository.getByName(firstName)
    }

    suspend fun getByMail(email:String): Boolean {
        return authRepository.getByMail(email)
    }


}