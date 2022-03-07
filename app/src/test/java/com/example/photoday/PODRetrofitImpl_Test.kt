package com.example.photoday

import com.example.photoday.repository.GetAPI
import com.example.photoday.repository.PODRetrofitImpl
import com.example.photoday.repository.PODServerResponseData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback

class PODRetrofitImplTest {



    private lateinit var repository: PODRetrofitImpl

    @Mock
    private lateinit var api: GetAPI

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = PODRetrofitImpl()
    }

    @Test
    fun getPictureOfTheDay() {
        val key = "key"
        val getPictureOfTheDay = PODServerResponseData(
            "",
            "",
            ""
            , ""
            , ""
            , ""
            , "")
        val dayAgo = "0"
        val call = mock(Call::class.java) as Callback<PODServerResponseData>

        `when`(api.getPictureOfTheDay(key, getPictureOfTheDay.toString()))

        //repository.getPictureOfTheDay(key, getPictureOfTheDay, dayAgo)

    }
}
