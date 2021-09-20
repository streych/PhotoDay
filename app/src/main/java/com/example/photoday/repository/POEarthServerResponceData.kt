package com.example.photoday.repository

import com.google.gson.annotations.SerializedName

data class POEarthServerResponceData(
    @field:SerializedName("date")val date: String?,
    @field:SerializedName("id")val id: String?,
    @field:SerializedName("url")val url: String?,
)