package com.example.chargily_pay

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChargilyApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v2/checkouts")
    fun createCheckout(
        @Body checkoutRequest: CheckoutRequest
    ): Call<CheckoutResponse>
}