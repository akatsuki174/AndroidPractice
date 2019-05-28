package com.example.listviewsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button1.setOnClickListener {
      val intent = Intent(this, ListSample1Activity::class.java)
      startActivity(intent)
    }
    button2.setOnClickListener {
      val intent = Intent(this, ListSample2Activity::class.java)
      startActivity(intent)
    }
    button3.setOnClickListener {
      val intent = Intent(this, ListSample3Activity::class.java)
      startActivity(intent)
    }
  }
}
