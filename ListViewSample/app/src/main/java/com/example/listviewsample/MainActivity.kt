package com.example.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    lvMenu.setOnItemClickListener { parent, view, position, id ->
      val item = parent.getItemAtPosition(position)
      val str = "あなたが選んだ定食は" + item
      Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
  }
}
