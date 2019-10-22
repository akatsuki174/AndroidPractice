package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeZones = TimeZone.getAvailableIDs()
        val adapter = ArrayAdapter<String>(this,
            R.layout.list_time_zone_row,
            R.id.timeZone,
            timeZones)
        timeZoneList.adapter = adapter
        timeZoneList.setOnItemClickListener { _, _, position, _ ->
            val timeZone = adapter.getItem(position)
            Toast.makeText(this, timeZone, Toast.LENGTH_SHORT).show()
        }
    }
}
