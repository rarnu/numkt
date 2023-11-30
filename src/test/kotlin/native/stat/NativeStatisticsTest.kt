package com.rarnu.numkt.test.native.stat

import com.rarnu.numkt.api.arange
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.native.libLoader
import com.rarnu.numkt.native.stat.NativeStatistics
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NativeStatisticsTest {

    @BeforeTest
    fun load() {
        libLoader("numkt_jni").load()
    }

    @Test
    fun testMedian() {
        val a = Numkt.ndarray(Numkt[Numkt[10, 7, 4], Numkt[3, 2, 1]])
        assertEquals(3.5, Numkt.stat.median(a))
    }

    @Test
    fun testSimpleAverage() {
        val a = Numkt.arange<Long>(1, 11, 1)
        assertEquals(Numkt.stat.mean(a), Numkt.stat.average(a))
    }

    @Test
    fun testAverageWithWeights() {
        val a = Numkt.arange<Long>(1, 11, 1)
        val weights = Numkt.arange<Long>(10, 0, -1)
        assertEquals(4.0, Numkt.stat.average(a, weights))
    }

    @Test
    fun testOfMeanFunctionOnAFlatNdarray() {
        val ndarray = Numkt.ndarray(Numkt[1, 2, 3, 4])
        assertEquals(2.5, NativeStatistics.mean(ndarray))
    }

    @Test
    fun testOfMeanFunctionOnA2dNdarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[1, 2], Numkt[3, 4]])
        assertEquals(2.5, NativeStatistics.mean(ndarray))
    }
}