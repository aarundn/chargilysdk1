package com.example.chargilysdk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chargily_pay.ChargilyCheckout
import com.example.chargily_pay.CheckoutRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val checkout = ChargilyCheckout()
        checkout.initialize("https://pay.chargily.net/test/",
            "test_sk_hcEcl0dMLQOCNWD6W9cfN2HLUJWY5GF5HUdzUBrx")

        checkout.createCheckout(
            CheckoutRequest(
                amount = 2000f,
                currency = "dzd",
                success_url = "https://your-server.com/success",
            ), onSuccess = { checkoutUrl ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl))
                startActivity(intent)
                Toast.makeText(this, "checkoutUrl: $checkoutUrl", Toast.LENGTH_SHORT).show()
            }, onError = { errorMessage ->
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            })

    }
}