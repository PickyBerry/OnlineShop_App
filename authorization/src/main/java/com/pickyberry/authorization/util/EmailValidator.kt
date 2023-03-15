package com.pickyberry.authorization.util

import android.text.TextUtils

object EmailValidator {
    fun isValid(email:String):Boolean{
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}