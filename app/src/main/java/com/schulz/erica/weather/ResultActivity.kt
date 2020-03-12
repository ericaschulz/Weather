package com.schulz.erica.weather

import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ListView as AndroidWidgetListView


class ResultActivity : CoroutineScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val zipCode = intent.extras.getString("zip_code")



        var latLongRetriever = LatLongRetriever()

        var listView = findViewById<AndroidWidgetListView>(R.id.result_list_view)

        var infoStrings = mutableListOf<String>()

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, infoStrings)

        listView?.adapter = adapter


        val weatherCallback = object : Callback<Weather> {

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {


                val summary = response.body()?.minutely?.summary

                val temperature = response.body()?.currently?.temperature

                val infoString = summary.toString() + " Current temperature is " + temperature + "F"


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


        latLongRetriever.getLatLong(zipCallback, zipCode)






    }


}

private fun <String> MutableList<String>.add(timezone: String?, summary: String?, temperature: String?) {



}












