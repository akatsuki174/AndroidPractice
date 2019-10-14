package com.example.asyncsample

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection


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
            @SuppressLint("SetTextI18n")
            tvCityName.text = "${cityName}の天気："

            val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
            val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)
            val receiver = WeatherInfoReceiver(tvWeatherTelop, tvWeatherDesc)
            receiver.execute(cityId)
        }
    }

    private fun createCity(name: String, id: String): HashMap<String, String> {
        val city = hashMapOf<String, String>()
        city["name"] = name
        city["id"] = id
        return city
    }

    private class WeatherInfoReceiver(_tvWeatherTelop: TextView, _tvWeatherDesc: TextView): AsyncTask<String, String, String>() {
        @SuppressLint("StaticFieldLeak")
        private val tvWeatherTelop: TextView = _tvWeatherTelop
        @SuppressLint("StaticFieldLeak")
        private val tvWeatherDesc: TextView = _tvWeatherDesc

        override fun doInBackground(vararg params: String?): String {
            val id = params[0]
            val urlStr = "https://weather.livedoor.com/forecast/webservice/json/v1?city=$id"
            var result = ""
            var con: HttpURLConnection? = null
            var inputStream: InputStream? = null
            try {
                val url = URL(urlStr)
                con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                con.connect()
                inputStream = con.inputStream
                result = is2String(inputStream)
            } catch(ex: IOException) {
                print("IOException")
            } catch (ex: MalformedURLException) {
                print("MalformedURLException")
            } finally {
                con?.disconnect()

                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (ex: IOException) {
                    }
                }
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var telop = ""
            var desc = ""
            try {
                val rootJSON = JSONObject(result)
                val descriptionJSON = rootJSON.getJSONObject("description")
                desc = descriptionJSON.getString("text")
                val forecasts = rootJSON.getJSONArray("forecasts")
                val forecastNow = forecasts.getJSONObject(0)
                telop = forecastNow.getString("telop")
            } catch (ex: JSONException) {
            }

            tvWeatherTelop.text = telop
            tvWeatherDesc.text = desc
        }

        private fun is2String(inputStream: InputStream): String {
            val reader = BufferedReader(inputStream.reader())
            val sb = StringBuilder()
            reader.use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
            }
            return sb.toString()
        }
    }


}
