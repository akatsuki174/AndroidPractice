package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val price = intent.getIntExtra("price", 0)
        val discount = intent.getIntExtra("discount", 0)
        expression_label.text = getString(R.string.expression, price, discount)

        val discountedPrice = price * (100 - discount) / 100
        result_label.text = getString(R.string.result, discountedPrice)
    }
}
