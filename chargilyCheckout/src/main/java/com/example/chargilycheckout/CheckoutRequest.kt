package com.example.chargily_pay

data class CheckoutRequest(
    val amount: Float,
    val currency: String,
    val success_url: String,
)
