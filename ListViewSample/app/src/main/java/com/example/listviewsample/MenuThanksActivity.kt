package com.example.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_menu_thanks.*

class MenuThanksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        tvMenuName.text = intent.getStringExtra("menuName")
        tvMenuPrice.text = intent.getStringExtra("menuPrice")
    }

    fun onBackButtonClick(view: View) {
        finish()
    }
}
