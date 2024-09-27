package com.example.chargily_pay

import android.content.Context
import android.content.Intent
import android.net.Uri
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.crypto.SecretKey

/** ChargilyCheckout class is for making chargily payment checkout **/
class ChargilyCheckout {
    // Public method to initiate checkout

    private lateinit var apiService: ChargilyApiService


    /** Public method to initiate checkout for making the request to
     * chargily pay platform using retrofit and okhttp
     * fill the first with chargily base Url
     * and the second with your secret key that you
     * find it in your chargily account in the dev corner **/
    fun initialize(baseUrl: String, secretKey: String) {

        val authInterceptor = AuthInterceptor(secretKey)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ChargilyApiService::class.java)
    }
    fun createCheckout(
        checkoutRequest: CheckoutRequest,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {

        // Build the authorization header
        val call = apiService.createCheckout( checkoutRequest)

        call.enqueue(object : Callback<CheckoutResponse> {
            override fun onResponse(
                call: Call<CheckoutResponse>,
                response: Response<CheckoutResponse>
            ) {
                if (response.isSuccessful) {
                    val checkoutUrl = response.body()?.checkout_url
                    checkoutUrl?.let {
                        onSuccess(it) // Provide checkout URL to developer if needed
                    }
                } else {
                    onError("Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                onError("Network error: ${t.message}")
            }
        })
    }
}