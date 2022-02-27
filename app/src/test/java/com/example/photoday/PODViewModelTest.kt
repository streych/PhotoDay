package com.example.photoday

import com.example.photoday.viewmodel.PODViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Test

class PODViewModelTest {

    private val viewModel = PODViewModel()


    @Test
    fun when_IntToStingValid() {
        val dayAgoInputOne = 1
        val dayAgoInputZero = 0
        val dayAgoOutput = "2022-02-26"
        assertNotNull(viewModel.getDaysAgo(dayAgoInputOne))
        assertEquals(dayAgoOutput, viewModel.getDaysAgo(dayAgoInputOne))
        assertNotEquals(dayAgoOutput, viewModel.getDaysAgo(dayAgoInputZero))
    }

}