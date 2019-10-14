package com.example.mediasample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SoundStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_start)

        val fromNotification = intent.getBooleanExtra("fromNotification", false)
        if (fromNotification) {
            val btPlay = findViewById<Button>(R.id.btPlay)
            val btStop = findViewById<Button>(R.id.btStop)
            btPlay.isEnabled = false
            btStop.isEnabled = true
        }

    }

    fun onPlayButtonClick(view: View) {
        val intent = Intent(this, SoundManageService::class.java)
        startService(intent)
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = false
        btStop.isEnabled = true
    }

    fun onStopButtonClick(view: View) {
        val intent = Intent(this, SoundManageService::class.java)
        stopService(intent)
        val btPlay = findViewById<Button>(R.id.btPlay)
        val btStop = findViewById<Button>(R.id.btStop)
        btPlay.isEnabled = true
        btStop.isEnabled = false
    }

}
