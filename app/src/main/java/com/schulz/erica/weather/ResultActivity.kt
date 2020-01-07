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



        var listView = findViewById<AndroidWidgetListView?>(R.id.result_list_view)

        var infoStrings = mutableListOf<String>()

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, infoStrings)

        listView?.adapter = adapter


        var latLongRetriever = LatLongRetriever()

        val zipCallback = object : Callback<ZipCodeToLatLong> {

            override fun onResponse(call: Call<ZipCodeToLatLong>, response: Response<ZipCodeToLatLong>) {

                val zip_code = response.body()?.zip_code
                val lat = response.body()?.lat
                val lng = response.body()?.lng
                val city = response.body()?.city
                val state = response.body()?.state

                //need to plug in zipcode response into the weather retriever...can we replace "search term" with zip_code??


            }

            override fun onFailure(call: Call<ZipCodeToLatLong>, t: Throwable) {

                println("No location info returned.")

            }

        }


        var retriever = WeatherRetriever()

        val callback = object : Callback<Weather> {

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

        val searchTerm = intent.extras.getString("searchTerm")




        //need to pass in a lat/long HERE
        retriever.getForecast(callback, searchTerm)


    }


}

private fun <String> MutableList<String>.add(timezone: String?, summary: String?, temperature: String?) {



}












