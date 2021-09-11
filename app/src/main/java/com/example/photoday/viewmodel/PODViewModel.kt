package com.example.photoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoday.BuildConfig
import com.example.photoday.repository.PODRetrofitImpl
import com.example.photoday.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.GsonBuilder

import com.google.gson.Gson




class PODViewModel(
    private val livaDataToObserver: MutableLiveData<PODData> = MutableLiveData(),
    private val rertofitIMPL: PODRetrofitImpl = PODRetrofitImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<PODData> {
        return livaDataToObserver
    }

    fun sendServerRequest(numberDay: Int) {
        livaDataToObserver.postValue(PODData.Loading(null))
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {

        } else {
           rertofitIMPL.getRetrofitImpl("https://api.nasa.gov/")
                .getPictureOfTheDay(apiKey)
                .enqueue(object : Callback<PODServerResponseData> {
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            livaDataToObserver.value =
                                PODData.Success(response.body() as PODServerResponseData)
                        } else {
                            val message = response.message()
                            //HW error max count
                            //Toast.makeText(,message, Toast.LENGTH_LONG).show()
                            //номер ошибки и вывод на экран
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }


    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formatter.format(calendar.time)
    }

}