package com.rarnu.numkt.test.kotlin.math

import com.rarnu.numkt.api.identity
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.kotlin.math.KEMath
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.D1
import com.rarnu.numkt.ndarray.data.get
import com.rarnu.numkt.ndarray.operations.times
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class KEMathTest {

    @Test
    fun testOfArgMaxFunctionDouble() {
        val ndarray1 = Numkt.ndarray(Numkt[0.008830892, 0.7638366187, -0.0401326368965, -0.269757419187])
        val ndarray2 = Numkt.ndarray(Numkt[0.0088308926050, 0.763836618743, Double.NaN, -0.2697574191872])

        assertEquals(1, Numkt.math.argMax(ndarray1))
        assertEquals(2, Numkt.math.argMax(ndarray2))
    }

    @Test
    fun testOfArgMaxFunctionWithAxis() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[50, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[0, 2], Numkt[2, 2]])
        val expectedWith1And2Axes = Numkt.ndarray(Numkt[Numkt[0, 1], Numkt[1, 1], Numkt[1, 1]])

        assertEquals(expectedWith0Axis, Numkt.math.argMaxD3(ndarray, axis = 0))
        assertEquals(expectedWith1And2Axes, Numkt.math.argMaxD3(ndarray, axis = 1))
        assertEquals(expectedWith1And2Axes, Numkt.math.argMaxD3(ndarray, axis = 2))
    }

    @Test
    fun testOfArgMinFunctionWithAxis() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[50, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[1, 0], Numkt[0, 0]])
        val expectedWith1And2Axes = Numkt.ndarray(Numkt[Numkt[1, 0], Numkt[0, 0], Numkt[0, 0]])

        assertEquals(expectedWith0Axis, Numkt.math.argMinD3(ndarray, axis = 0))
        assertEquals(expectedWith1And2Axes, Numkt.math.argMinD3(ndarray, axis = 1))
        assertEquals(expectedWith1And2Axes, Numkt.math.argMinD3(ndarray, axis = 2))
    }

    @Test
    fun testOfMaxFunctionWithAxis() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[7, 9], Numkt[10, 11]])
        val expectedWith1Axis = Numkt.ndarray(Numkt[Numkt[1, 4], Numkt[6, 8], Numkt[10, 11]])
        val expectedWith2Axis = Numkt.ndarray(Numkt[Numkt[3, 4], Numkt[5, 8], Numkt[9, 11]])

        assertEquals(expectedWith0Axis, Numkt.math.max(ndarray, axis = 0))
        assertEquals(expectedWith1Axis, Numkt.math.max(ndarray, axis = 1))
        assertEquals(expectedWith2Axis, Numkt.math.max(ndarray, axis = 2))
    }

    @Test
    fun testOfMinFunctionWithAxis() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[0, 3], Numkt[1, 4]])
        val expectedWith1Axis = Numkt.ndarray(Numkt[Numkt[0, 3], Numkt[2, 5], Numkt[7, 9]])
        val expectedWith2Axis = Numkt.ndarray(Numkt[Numkt[0, 1], Numkt[2, 6], Numkt[7, 10]])

        assertEquals(expectedWith0Axis, Numkt.math.min(ndarray, axis = 0))
        assertEquals(expectedWith1Axis, Numkt.math.min(ndarray, axis = 1))
        assertEquals(expectedWith2Axis, Numkt.math.min(ndarray, axis = 2))
    }

    @Test
    fun testOfSumFunctionWithAxisOnFlatNdarray() {
        val ndarray = Numkt.ndarray(Numkt[0, 3, 1, 4])
        assertFailsWith<IllegalArgumentException> { KEMath.sum<Int, D1, D1>(ndarray, 0) }
    }

    @Test
    fun testOfSumFunctionWithAxisOn2dNdarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[0, 3], Numkt[1, 4]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[1, 7])
        assertEquals(expectedWith0Axis, KEMath.sumD2(ndarray, 0))

        val expectedWith1Axis = Numkt.ndarray(Numkt[3, 5])
        assertEquals(expectedWith1Axis, KEMath.sumD2(ndarray, 1))
    }

    @Test
    fun testOfSumFunctionWithAxisOn3dNdarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[9, 17], Numkt[17, 23]])
        val expectedWith1Axis = Numkt.ndarray(Numkt[Numkt[1, 7], Numkt[8, 13], Numkt[17, 20]])
        val expectedWith2Axis = Numkt.ndarray(Numkt[Numkt[3, 5], Numkt[7, 14], Numkt[16, 21]])

        assertEquals(expectedWith0Axis, Numkt.math.sumD3(ndarray, axis = 0))
        assertEquals(expectedWith1Axis, Numkt.math.sumD3(ndarray, axis = 1))
        assertEquals(expectedWith2Axis, Numkt.math.sumD3(ndarray, axis = 2))
    }

    @Test
    fun testOfSumFunctionWithThirdAxisOn2dNdarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[0, 3], Numkt[1, 4]])
        assertFailsWith<IllegalArgumentException> { KEMath.sumD2(ndarray, 2) }
    }

    @Test
    fun testOfCumSumFunctionWithAxisOn3dNdarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])
        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 8], Numkt[7, 12]], Numkt[Numkt[9, 17], Numkt[17, 23]]])
        val expectedWith1Axis = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 7]], Numkt[Numkt[2, 5], Numkt[8, 13]], Numkt[Numkt[7, 9], Numkt[17, 20]]])
        val expectedWith2Axis = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 5]], Numkt[Numkt[2, 7], Numkt[6, 14]], Numkt[Numkt[7, 16], Numkt[10, 21]]])

        assertEquals(expectedWith0Axis, Numkt.math.cumSum(ndarray, axis = 0))
        assertEquals(expectedWith1Axis, Numkt.math.cumSum(ndarray, axis = 1))
        assertEquals(expectedWith2Axis, Numkt.math.cumSum(ndarray, axis = 2))
    }

    @Test
    fun testMultiplicationOfComplex2dNdarray() {
        val result = ComplexFloat(-2) * Numkt.identity(3)
        assertEquals(ComplexFloat(-2), result[0, 0])
    }
}