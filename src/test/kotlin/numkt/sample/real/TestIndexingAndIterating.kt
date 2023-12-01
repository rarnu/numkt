package com.rarnu.numkt.test.numkt.sample.real

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.data.get
import com.rarnu.numkt.ndarray.data.rangeTo
import com.rarnu.numkt.ndarray.operations.joinToString
import kotlin.test.Test
import kotlin.test.assertEquals

class TestIndexingAndIterating {
    @Test
    fun simpleIndexing() {
        val a = Numkt.ndarray(Numkt[1, 2, 3])
        val b = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        assertEquals(3, a[2])
        assertEquals(6.0, b[1, 2], 0.01)
    }

    @Test
    fun slice1() {
        val b = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        assertEquals(Numkt.ndarray(Numkt[2.1, 5.0]), b[0..<2, 1])
    }

    @Test
    fun slice2() {
        val b = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        assertEquals(b[1], b[1, 0..2..1])
    }

    @Test
    fun iterating() {
        val b = Numkt.ndarray(Numkt[Numkt[1.5, 2.1, 3.0], Numkt[4.0, 5.0, 6.0]])
        assertEquals(b.joinToString(), "1.5, 2.1, 3.0, 4.0, 5.0, 6.0")
    }
}