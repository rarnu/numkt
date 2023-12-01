package com.rarnu.numkt.test.numkt.sample.real

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.operations.times
import kotlin.test.Test
import kotlin.test.assertEquals

class TestOverview {
    @Test
    fun createKotlinMatrix() {
        val a = listOf(listOf(1, 2, 3), listOf(4, 5, 6))
        val b = listOf(listOf(7, 8, 9), listOf(10, 11, 12))
        val c = MutableList(2) { MutableList(3) { 0 } }
        for (i in a.indices) {
            for (j in a.first().indices) {
                c[i][j] = a[i][j] * b[i][j]
            }
        }
        assertEquals("$c", "[[7, 16, 27], [40, 55, 72]]")
    }

    @Test
    fun createMultikMatrix() {
        val a = Numkt.ndarray(Numkt[Numkt[1, 2, 3], Numkt[4, 5, 6]])
        val b = Numkt.ndarray(Numkt[Numkt[7, 8, 9], Numkt[10, 11, 12]])
        val c = a * b
        assertEquals("$c", "[[7, 16, 27],\n[40, 55, 72]]")
    }
}