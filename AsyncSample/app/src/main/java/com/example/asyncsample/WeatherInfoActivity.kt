package com.example.asyncsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class WeatherInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val cityList = arrayListOf<Map<String, String>>()
        cityList.add(createCity("大阪", "270000"))
        cityList.add(createCity("神戸", "280010"))
        cityList.add(createCity("明石", "280000"))
        cityList.add(createCity("津", "290010"))
        cityList.add(createCity("京都", "180010"))
        cityList.add(createCity("豊岡", "380010"))
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this, cityList, android.R.layout.simple_expandable_list_item_1, from, to)
        lvCityList.adapter = adapter
        lvCityList.setOnItemClickListener { parent, _, position, _ ->
            @Suppress("UNCHECKED_CAST")
            val item = parent.getItemAtPosition(position) as Map<String, String>
            val cityName = item["name"]
            val cityId = item["id"]
            val tvCityName = findViewById<TextView>(R.id.tvCityName)
            tvCityName.text = cityName + "の天気："
        }
    }

    private fun createCity(name: String, id: String): HashMap<String, String> {
        val city = hashMapOf<String, String>()
        city["name"] = name
        city["id"] = id
        return city
    }
}
