package com.schulz.erica.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ListView as AndroidWidgetListView


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val zipCode = intent.extras.getString("zip_code")


        var latLong = mutableListOf<String>()
        var latLongRetriever = LatLongRetriever()

        var listView = findViewById<AndroidWidgetListView?>(R.id.result_list_view)

        var infoStrings = mutableListOf<String>()

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, infoStrings)

        listView?.adapter = adapter


        val weatherCallback = object : Callback<Weather> {

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {

                val timezone = response.body()?.timezone

                val summary = response.body()?.minutely?.summary

                val temperature = response.body()?.currently?.temperature

                val infoString = "${timezone}:${summary}:${temperature}"



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

                //val latLng = "${lat}:${lng}"

                latLong.add(lat?: "0.0")
                latLong.add(lng?: "0.0")


                var weatherRetriever = WeatherRetriever()
                weatherRetriever.getForecast(weatherCallback, latLong)



            }

            override fun onFailure(call: Call<ZipCodeToLatLong>, t: Throwable) {

                println("No location info returned.")

            }



    }


        latLongRetriever.getLatLong(zipCallback, zipCode)






    }


}

private fun <String> MutableList<String>.add(timezone: String?, summary: String?, temperature: String?) {



}












