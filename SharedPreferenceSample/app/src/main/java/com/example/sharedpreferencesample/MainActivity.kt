package com.example.sharedpreferencesample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("file_name", Context.MODE_PRIVATE)
        val storedText = pref.getString("key", "未登録")
        editText.setText(storedText)
        button.setOnClickListener {
            val inputText = editText.text.toString()
            pref.edit().putString("key", inputText).apply()
        }
    }
}
