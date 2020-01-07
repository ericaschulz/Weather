package com.schulz.erica.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ZipCodeAPI {

    @GET("/dFH8pYUhT1UF3SYhbMVsjaMbdykIjtnB6gVdC1JmpeeeJVmsFOtCbB3jnr8XaXNw/info.json/60614/degrees")

    fun getLatLong(): Call<ZipCodeToLatLong>
}

    class ZipCodeToLatLong(val zip_code: String, val lat: String, val lng: String, val city: String, val state: String)



    class LatLongRetriever {

        private val latLongService: ZipCodeAPI

        init{

            val retrofit =  Retrofit.Builder().baseUrl("https://www.zipcodeapi.com/rest")
                .addConverterFactory(GsonConverterFactory.create()).build()
                latLongService = retrofit.create(ZipCodeAPI::class.java)
        }
            fun getLatLong(zipCallback: Callback<ZipCodeToLatLong>, searchTerm: String) {
            val call = latLongService.getLatLong()
            call.enqueue(zipCallback)

        }


    }









