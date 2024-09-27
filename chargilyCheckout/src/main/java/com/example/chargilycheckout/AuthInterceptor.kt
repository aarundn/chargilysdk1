package com.example.chargily_pay

import okhttp3.Interceptor
import okhttp3.Response
import javax.crypto.SecretKey

class AuthInterceptor(private val secretKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer $secretKey")
            .build()
        return chain.proceed(request)
    }
}