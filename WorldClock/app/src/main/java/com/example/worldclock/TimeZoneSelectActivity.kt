package com.example.worldclock

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        setResult(Activity.RESULT_CANCELED)

        val adapter = TimeZoneAdapter(this)
        clockList.adapter = adapter
        
        clockList.setOnItemClickListener { parent, view, position, id ->
            val timeZone = adapter.getItem(position)
            val result = Intent()
            result.putExtra("timeZone", timeZone)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}
