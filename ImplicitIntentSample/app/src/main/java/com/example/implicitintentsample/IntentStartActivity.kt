package com.example.implicitintentsample

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class IntentStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_start)
    }

    fun onMapSearchButtonClick(view: View) {
        val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
        var searchWord = etSearchWord.text.toString()

        try {
            searchWord = URLEncoder.encode(searchWord, "UTF-8")
            val uriStr = "geo:0.0?q=$searchWord"
            val uri = Uri.parse(uriStr)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        } catch (ex: UnsupportedEncodingException) {
            Log.e("IntentStartActivity", "検索キーワード変換失敗", ex)
        }
    }
}
