package com.example.photoday.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureTypeTheDayAPI {
    //https://api.nasa.gov/planetary/apod?date=2021-09-10&api_key=MmffcfCDhJGoq6tSfctFG6Ol7gdoIrbBe2gg0tj4
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String) : Call<PODServerResponseData>
}