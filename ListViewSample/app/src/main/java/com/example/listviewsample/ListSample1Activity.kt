package com.example.listviewsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_sample_1.*

class ListSample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sample_1)

        lvMenu.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position)
            val str = "あなたが選んだ定食は$item"
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        }
    }
}
