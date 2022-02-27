package com.example.photoday.repository

import com.google.gson.annotations.SerializedName

data class SolarFlareResponseDate(
    @field:SerializedName("activeRegionNum")val activeRegionNum: Int?,
    @field:SerializedName("beginTime")val beginTime: String?,
    @field:SerializedName("classType")val classType: String?,
    @field:SerializedName("endTime")val endTime: Any? = null,
    @field:SerializedName("flrID")val flrID: String?,
    @field:SerializedName("link")val link: String?,
    @field:SerializedName("peakTime")val peakTime: String?,
    @field:SerializedName("sourceLocation")val sourceLocation: String?
)