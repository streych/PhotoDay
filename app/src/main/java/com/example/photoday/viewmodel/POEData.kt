package com.example.photoday.viewmodel

import com.example.photoday.repository.POEarthServerResponceData

sealed class POEData {
    data class Success(val serverResponseData: POEarthServerResponceData) : POEData()
    data class Error(val error: Throwable) : POEData()
    data class Loading(val progress: Int?) : POEData()
}
