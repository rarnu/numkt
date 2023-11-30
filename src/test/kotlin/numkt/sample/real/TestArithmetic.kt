package com.rarnu.numkt.test.numkt.sample.real

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.operations.*
import org.junit.Assert.assertEquals
import org.junit.Test


class TestArithmetic {

    @Test
    fun arithWithScalars() {
        val a = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        assertEquals(listOf(4.8, 5.4, 6.3, 7.3, 8.3, 9.3), (3.3 + a).toList())
        assertEquals(listOf(3.0, 4.2, 6.0, 8.0, 10.0, 12.0), (a * 2.0).toList())
    }

    @Test
    fun divWithNdarrays() {
        val a = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        val b = Numkt.ndarray(Numkt[Numkt[1.0, 1.3, 3.0], Numkt[4.0, 9.5, 5.0]])
        val actual = a / b
        assertEquals(true, intArrayOf(2, 3).contentEquals(actual.shape))
        assertEquals(listOf(1.5, 1.6153846153846154, 1.0, 1.0, 0.5263157894736842, 1.2), actual.toList())
    }

    @Test
    fun mulWithNdarrays() {
        val a = Numkt.ndarray(Numkt[Numkt[0.5, 0.8, 0.0], Numkt[0.0, -4.5, 1.0]])
        val b = Numkt.ndarray(Numkt[Numkt[1.0, 1.3, 3.0], Numkt[4.0, 9.5, 5.0]])
        val actual = a * b
        assertEquals(true, intArrayOf(2, 3).contentEquals(actual.shape))
        assertEquals(listOf(0.5, 1.04, 0.0, 0.0, -42.75, 5.0), actual.toList())
    }

    @Test
    fun inplaceArithOps() {
        val a = Numkt.ndarray(Numkt[Numkt[1, 2], Numkt[3, 4]])
        val b = Numkt.ndarray(Numkt[Numkt[4, 0], Numkt[7, 5]])
        a += b
        a *= 3
        assertEquals(listOf(15, 6, 30, 27), a.toList())
    }
}