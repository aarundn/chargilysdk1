[![](https://jitpack.io/v/aarundn/chargilysdk1.svg)](https://jitpack.io/#aarundn/chargilysdk1)
# Chargily SDK

Chargily SDK simplifies the integration of the Chargily payment gateway into your Android applications.

## Getting Started

To use this SDK, follow these steps:
### Step 1: Add Jitpack link

Add the following Jitpack link to your `settings.gradle` (Module: app) file:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        `maven { url 'https://jitpack.io' }`
    }
}
```
### Step 2: Add Dependency

Add the following dependency to your `build.gradle` (Module: app) file:

```gradle
dependencies {
    implementation 'com.github.aarundn:chargilysdk1:1.0-beta'
}
```
### Step 3: Initialize Chargily SDK

Initialize the SDK with the base URL and your secret key:

```kotlin
val chargilyPay = ChargilyCheckout()

chargilyPay.initialize(
    "BaseUrlXXXXXXXXXXXXXXXXXX", 
    "SecretKeyXXXXXXXXXXXXXXXXXXX"
)
```
### Step 4: Create Checkout

Use the `createCheckout` method to create a new checkout session. This requires the amount, currency, and success URL:

```kotlin
chargilyPay.createCheckout(
    CheckoutRequest(
        amount = 2000f,
        currency = "dzd",
        success_url = "https://your-server.com/success"
    ), 
    onSuccess = { checkoutUrl ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl))
        startActivity(intent)
        Toast.makeText(this, "Checkout URL: $checkoutUrl", Toast.LENGTH_SHORT).show()
    }, 
    onError = { errorMessage ->
        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
    }
)

```
### Step 5: Handle Success and Error

Handle the success and error cases within your `createCheckou`t method.
Upon success, the SDK will return a checkout URL that can be opened using an `Intent`. In case of failure, the error message will be displayed:

```kotlin
onSuccess = { checkoutUrl ->
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl))
    startActivity(intent)
    Toast.makeText(this, "Checkout URL: $checkoutUrl", Toast.LENGTH_SHORT).show()
},
onError = { errorMessage ->
    Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
}
```
### Step 6: Customize Your Integration

Make sure to replace the example values with your actual secret key and URLs:

* Secret Key: Replace `"SecretKeyXXXXXXXXXXXXXXXXXXX"` with your actual Chargily secret key.
* Success URL: Update the success URL to point to your server or frontend endpoint.

### Example

Here’s a complete example of the SDK in use within an Android activity:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chargilyPay = ChargilyCheckout()

        chargilyPay.initialize(
            "BaseUrlXXXXXXXXXXXXXXXXXX", 
            "SecretKeyXXXXXXXXXXXXXXXXXXX"
        )

        chargilyPay.createCheckout(
            CheckoutRequest(
                amount = 2000f,
                currency = "dzd",
                success_url = "https://your-server.com/success"
            ),
            onSuccess = { checkoutUrl ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(checkoutUrl))
                startActivity(intent)
                Toast.makeText(this, "Checkout URL: $checkoutUrl", Toast.LENGTH_SHORT).show()
            },
            onError = { errorMessage ->
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

```
### Notes:

* Replace the base URL and secret key with the values from your Chargily account.
* Ensure to change the `success_url` to your actual server endpoint.

### License
This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
