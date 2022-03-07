package com.example.photoday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.photoday.BuildConfig
import com.example.photoday.repository.PODRetrofitImpl
import com.example.photoday.repository.PODServerResponseData
import com.example.photoday.repository.POEarthServerResponceData
import com.example.photoday.repository.SolarFlareResponseDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

open class PODViewModel(
    private val livaDataToObserver: MutableLiveData<PODData> = MutableLiveData(),
    private val livaDataToObserver1: MutableLiveData<POEData> = MutableLiveData(),
    private val rertofitIMPL: PODRetrofitImpl = PODRetrofitImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<PODData> {
        return livaDataToObserver
    }

    fun getLiveData1(): LiveData<POEData> {
        return livaDataToObserver1
    }

    fun sendServerRequest(numberDay: Int) {
        livaDataToObserver.postValue(PODData.Loading(null))
        livaDataToObserver1.postValue(POEData.Loading(null))
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {

        } else {
            rertofitIMPL.getPictureOfTheDay(apiKey, PODCallback, getDaysAgo(numberDay))
            rertofitIMPL.getSolarFlareToday(apiKey,solarFlareCallback,"2021-09-01")
            rertofitIMPL.getEarthToday(apiKey,earthOfTheDay, "2021-09-01")
        }
    }

    private val PODCallback = object : Callback<PODServerResponseData> {
        override fun onResponse(
            call: Call<PODServerResponseData>,
            response: Response<PODServerResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                livaDataToObserver.value =
                    PODData.Success(response.body() as PODServerResponseData)
            } else {
                val message = response.message()
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }

    private val earthOfTheDay = object : Callback<POEarthServerResponceData> {
        override fun onResponse(
            call: Call<POEarthServerResponceData>,
            response: Response<POEarthServerResponceData>
        ) {
            if (response.isSuccessful && response.body() != null) {
               livaDataToObserver1.value = POEData.Success(response.body() as POEarthServerResponceData)
            } else {
                val message = response.message()
            }
        }

        override fun onFailure(call: Call<POEarthServerResponceData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }

    private val solarFlareCallback = object : Callback<List<SolarFlareResponseDate>> {
        override fun onResponse(
            call: Call<List<SolarFlareResponseDate>>,
            response: Response<List<SolarFlareResponseDate>>
        ) {
            if (response.isSuccessful && response.body() != null) {
                //livaDataToObserver.value = PODData.Success(response.body() as PODServerResponseData)
            } else {
                //val message = response.message()
            }
        }
        override fun onFailure(call: Call<List<SolarFlareResponseDate>>, t: Throwable) {
            livaDataToObserver.postValue(PODData.Error(t))
        }
    }


    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formatter.format(calendar.time)
    }

}