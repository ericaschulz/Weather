package com.schulz.erica.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface WeatherAPI {


    @GET("/forecast/b6005c3a5f494b5ed538d4ef32ebd307/[latitude],[longitude")


//    "37.8267,-122.4233"



    fun getForecast() : Call<Weather>
}

class Weather(var timezone: String, var currently: CurrentWeather, var minutely: Summary)
class CurrentWeather(var temperature: String)
class Summary(var summary: String)





class WeatherRetriever {

    private val service: WeatherAPI

    init{

     val retrofit =  Retrofit.Builder().baseUrl("https://api.darksky.net")
            .addConverterFactory(GsonConverterFactory.create()).build()
            service = retrofit.create(WeatherAPI::class.java)
    }
    fun getForecast(callback: Callback<Weather>, searchTerm: String) {
        val call = service.getForecast()
        call.enqueue(callback)

    }


}