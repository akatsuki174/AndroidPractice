package com.example.toolbarsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            val intent = Intent(this, ScrollArticleActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, DynamicScrollArticleActivity::class.java)
            startActivity(intent)
        }
    }
}
