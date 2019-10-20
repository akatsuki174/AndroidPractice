package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate.setOnClickListener {
            var isValid = true

            val priceText = price.text.toString()
            if (priceText.isEmpty()) {
                price.error = getString(R.string.price_error)
                isValid = false
            }

            val discountText = discount.text.toString()
            if (discountText.isEmpty()) {
                discount.error = getString(R.string.discount_error)
                isValid = false
            }

            if (isValid) {
                val priceValue = priceText.toInt()
                val discountValue = discountText.toInt()

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("price", priceValue)
                intent.putExtra("discount", discountValue)
                startActivity(intent)
            }
        }
    }
}
