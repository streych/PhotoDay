package com.example.photoday.repository

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PODRetrofitImpl {

    private val baseUrl = "https://api.nasa.gov/"
    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(GetAPI::class.java)
    }

    fun getPictureOfTheDay(
        apiKey: String,
        podCallback: Callback<PODServerResponseData>,
        daysAgo: String
    ) {
        api.getPictureOfTheDay(apiKey, daysAgo).enqueue(podCallback)
    }

    fun getSolarFlareToday(
        apiKey: String,
        podCallback: Callback<List<SolarFlareResponseDate>>,
        startDate: String = "2021-09-07"
    ) {
        api.getSolarFlareToday(apiKey, startDate).enqueue(podCallback)
    }

    fun getEarthToday(
        apiKey: String,
        earthCallback:  Callback<POEarthServerResponceData>,
        date: String
    ) {
        api.getEarthOfTheDay(apiKey, -95.33, 29.78, date, 0.10).enqueue(earthCallback)
    }


}



