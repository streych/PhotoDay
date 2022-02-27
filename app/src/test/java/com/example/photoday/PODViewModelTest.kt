package com.example.photoday

import com.example.photoday.viewmodel.PODViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Test

class PODViewModelTest {

    private val viewModel = PODViewModel()


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