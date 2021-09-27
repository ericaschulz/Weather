package com.schulz.erica.weather

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ListView as AndroidWidgetListView


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val zipCode = intent.extras?.getString("zip_code")

        var latLongRetriever = LatLongRetriever()

        var listView = findViewById<AndroidWidgetListView>(R.id.result_list_view)

        var infoStrings = mutableListOf<String>()

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, infoStrings)

        listView?.adapter = adapter


        val weatherCallback = object : Callback<Weather> {

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {

                val currentSummary = response.body()?.minutely?.summary

                val temperature = response.body()?.currently?.temperature

                val apparentTemperature = response.body()?.currently?.apparentTemperature

                val humidity = response.body()?.currently?.humidity

                val hourly = response.body()?.hourly?.summary

                val daily = response.body()?.daily?.summary

                val infoString = currentSummary.toString() + "..presently. The current temperature is " + temperature + "F, and the apparent" +
                        " (real feel) temperature is " + apparentTemperature+
                        "F. The humidity is " +humidity+ ". The forecast for the rest of the day is: " +hourly+ "Throughout the week: " +daily+""


                infoStrings.add(infoString)

                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {

                println("Failed. Didn't work.")

            }

        }
        val zipCallback = object : Callback<ZipCodeToLatLong> {

            override fun onResponse(call: Call<ZipCodeToLatLong>, response: Response<ZipCodeToLatLong>) {


                val lat = response.body()?.lat
                val lng = response.body()?.lng
                val city: String? = response.body()?.city
                val state: String? = response.body()?.state

                val cityState: String? =city.toString() + ", " + state.toString()

                city_name.text = cityState



                var weatherRetriever = WeatherRetriever()
                weatherRetriever.getForecast(weatherCallback, lat, lng)



            }

            override fun onFailure(call: Call<ZipCodeToLatLong>, t: Throwable) {

                println("No location info returned.")

            }



    }


        if (zipCode != null) {
            latLongRetriever.getLatLong(zipCallback, zipCode)
        }




    }


}













