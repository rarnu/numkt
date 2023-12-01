package com.rarnu.numkt.test.numkt.ndarray.complex

import com.rarnu.numkt.api.d2arrayIndices
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.complex.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestComplexMultiArray {

    @Test
    fun reAndImPropertiesReturnsRealAndImaginaryPortionOfFloatComplexNumberArray() {
        val complex = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexFloat(i, j) }
        val real = complex.re
        val im = complex.im
        val expectedReal = Numkt.d2arrayIndices(3, 3) { i, _ -> i.toFloat() }
        val expectedIm = Numkt.d2arrayIndices(3, 3) { _, j -> j.toFloat() }

        assertEquals(complex.shape, real.shape)
        assertEquals(complex.shape, im.shape)
        assertEquals(expectedReal, real)
        assertEquals(expectedIm, im)
    }

    @Test
    fun reAndImPropertiesReturnsRealAndImaginaryPortionOfDoubleComplexNumberArray() {
        val complex = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexDouble(i, j) }
        val real = complex.re
        val im = complex.im
        val expectedReal = Numkt.d2arrayIndices(3, 3) { i, _ -> i.toDouble() }
        val expectedIm = Numkt.d2arrayIndices(3, 3) { _, j -> j.toDouble() }

        assertEquals(complex.shape, real.shape)
        assertEquals(complex.shape, im.shape)
        assertEquals(expectedReal, real)
        assertEquals(expectedIm, im)
    }

    @Test
    fun reAndImPropertiesWorkForEmptyComplexDoubleArrays() {
        val complex = Numkt.ndarray(emptyList<ComplexDouble>())
        val real = complex.re
        val im = complex.im

        assertEquals(complex.shape, real.shape)
        assertEquals(complex.shape, im.shape)
        assertTrue(real.isEmpty())
        assertTrue(im.isEmpty())
    }

    @Test
    fun reAndImPropertiesWorkForEmptyComplexFloatArrays() {
        val complex = Numkt.ndarray(emptyList<ComplexFloat>())
        val real = complex.re
        val im = complex.im

        assertEquals(complex.shape, real.shape)
        assertEquals(complex.shape, im.shape)
        assertTrue(real.isEmpty())
        assertTrue(im.isEmpty())
    }

    @Test
    fun conjReturnsArrayOfConjugatedComplexDoubles() {
        val complex = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexDouble(i, j) }
        val expected = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexDouble(i, -j) }
        val actual = complex.conj()

        assertEquals(complex.shape, actual.shape)
        assertEquals(expected, actual)
    }

    @Test
    fun conjReturnsArrayOfConjugatedComplexFloats() {
        val complex = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexFloat(i, j) }
        val expected = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexFloat(i, -j) }
        val actual = complex.conj()

        assertEquals(complex.shape, actual.shape)
        assertEquals(expected, actual)
    }

    @Test
    fun conjForEmptyComplexFloatArrays() {
        val complex = Numkt.ndarray(emptyList<ComplexFloat>())
        val conj = complex.conj()

        assertEquals(complex.shape, conj.shape)
        assertTrue(conj.isEmpty())
    }

    @Test
    fun conjForEmptyComplexDoubleArrays() {
        val complex = Numkt.ndarray(emptyList<ComplexDouble>())
        val conj = complex.conj()

        assertEquals(complex.shape, conj.shape)
        assertTrue(conj.isEmpty())
    }
}
