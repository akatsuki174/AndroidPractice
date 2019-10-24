package com.example.worldclock

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defaultTimeZone = TimeZone.getDefault()
        timeZone.text = defaultTimeZone.displayName

        add.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            startActivityForResult(intent, 1)
        }

        showWorldClocks()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1
            && resultCode == Activity.RESULT_OK
            && data != null
        ) {
            val timeZone = data.getStringExtra("timeZone")
            val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val timeZones = pref.getStringSet("time_zone", mutableSetOf())?.toMutableSet()
                ?: mutableSetOf()
            timeZones.add(timeZone)
            pref.edit().putStringSet("time_zone", timeZones.toSet()).apply()
            showWorldClocks()
        }
    }

    private fun showWorldClocks() {
        val pref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val timeZones = pref.getStringSet("time_zone", null) ?: setOf()
        clockList.adapter = TimeZoneAdapter(this, timeZones.toTypedArray())
    }
}
