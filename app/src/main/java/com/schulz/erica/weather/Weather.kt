package com.schulz.erica.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {


    @GET("{latitude},{longitude}")


    fun getForecast(@Path("latitude") latitude:String?,@Path("longitude") longitude:String?): Call<Weather>
}

class Weather(var currently: CurrentWeather, var minutely: Summary)
class CurrentWeather(var temperature: String)
class Summary(var summary: String)



class WeatherRetriever {

    private val service: WeatherAPI

    init{

     val retrofit =  Retrofit.Builder()
         .baseUrl(Constants.DS_BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         service = retrofit.create(WeatherAPI::class.java)
    }


    fun getForecast(callback: Callback<Weather>,lat: String?, lng: String?) {
        val call = service.getForecast(lat,lng)
        call.enqueue(callback)

    }


}