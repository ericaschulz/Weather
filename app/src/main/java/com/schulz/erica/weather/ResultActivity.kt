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

        retriever.getForecast(callback)


    }


}

private fun <String> MutableList<String>.add(timezone: String?, summary: String?, temperature: String?) {



}












