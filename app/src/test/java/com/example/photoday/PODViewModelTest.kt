package com.example.photoday

import androidx.lifecycle.MutableLiveData
import com.example.photoday.repository.PODRetrofitImpl
import com.example.photoday.viewmodel.PODData
import com.example.photoday.viewmodel.PODViewModel
import com.example.photoday.viewmodel.POEData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PODViewModelTest {


    private lateinit var viewModel: PODViewModel

    @Mock
    private lateinit var livaDataToObserver: MutableLiveData<PODData>

    @Mock
    private lateinit var livaDataToObserver1: MutableLiveData<POEData>

    @Mock
    private lateinit var rertofitIMPL: PODRetrofitImpl

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = PODViewModel(livaDataToObserver, livaDataToObserver1, rertofitIMPL)
    }

    @Test
    fun when_DateNotNull() {
        val dayAgoInputOne = 1
        assertNotNull(viewModel.getDaysAgo(dayAgoInputOne))
    }

    @Test
    fun when_DateEquals() {
        val dayAgoInputOne = 1
        val dayAgoOutput = "2022-02-26"
        assertEquals(dayAgoOutput, viewModel.getDaysAgo(dayAgoInputOne))
    }
    @Test
    fun when_DateNotEqualsToday() {
        val dayAgoInputZero = 0
        val dayAgoOutput = "2022-02-26"
        assertNotEquals(dayAgoOutput, viewModel.getDaysAgo(dayAgoInputZero))
    }


}