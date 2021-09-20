package com.example.photoday.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<PODServerResponseData>

    //https://api.nasa.gov/planetary/earth/assets?lon=-95.33&lat=29.78&date=2021-09-20&&dim=0.10&api_key=MmffcfCDhJGoq6tSfctFG6Ol7gdoIrbBe2gg0tj4
    @GET("planetary/earth/assets")
    fun getEarthOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("lon") lon: Double = -95.33,
        @Query("lat") lat: Double = 29.78,
        @Query("date") date: String,
        @Query("dim") dim: Double = 0.10
    ): Call<POEarthServerResponceData>

    //https://api.nasa.gov/DONKI/FLR?startDate=2021-01-01&endDate=2021-09-20&api_key=MmffcfCDhJGoq6tSfctFG6Ol7gdoIrbBe2gg0tj4

    @GET("DONKI/FLR")
    fun getSolarFlareToday(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate: String = "2021-09-20"
    ): Call<List<SolarFlareResponseDate>>
}