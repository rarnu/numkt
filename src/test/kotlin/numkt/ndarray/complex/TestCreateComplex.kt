package com.rarnu.numkt.test.numkt.ndarray.complex

import com.rarnu.numkt.ndarray.complex.*
import org.junit.Assert.assertEquals
import org.junit.Test


class TestCreateComplex {

    @Test
    fun testEasyComplexCreation() {
        assertEquals(Complex.i(1.12f), 1.12f.i)
        assertEquals(Complex.i(3.33), 3.33.i)
        assertEquals(ComplexFloat(1, 1), 1 + 1f.i)
        assertEquals(ComplexDouble(3, 7), 3 + 7.0.i)
    }
}