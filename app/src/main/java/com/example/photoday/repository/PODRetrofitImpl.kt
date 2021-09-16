package com.example.photoday.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PODRetrofitImpl {


    fun getRetrofitImpl(baseUrl: String): PictureTypeTheDayAPI {

        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            return podRetrofit.create(PictureTypeTheDayAPI::class.java)
    }
}