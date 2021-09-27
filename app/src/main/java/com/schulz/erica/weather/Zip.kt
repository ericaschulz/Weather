package com.schulz.erica.weather


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface Zip {

    @GET("info.json/{zip_code}/degrees")

    fun getLatLong(@Path("zip_code") zipCode: String): Call<ZipCodeToLatLong>

}
    class ZipCodeToLatLong(val lat: String?, val lng: String?, val city: String?, val state: String?)


    class LatLongRetriever {

        private val latLongService: Zip

        init{

            val retrofit =  Retrofit.Builder().baseUrl(Constants.ZIP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                latLongService = retrofit.create(Zip::class.java)
        }


        fun getLatLong(zipCallback: Callback<ZipCodeToLatLong>, zipCode: String) {

            val call = latLongService.getLatLong(zipCode)

            call.enqueue(zipCallback)

        }


    }









