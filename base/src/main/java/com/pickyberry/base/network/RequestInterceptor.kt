package com.pickyberry.base.network

import okhttp3.Interceptor

//RequestInterceptor for retrofit
object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}