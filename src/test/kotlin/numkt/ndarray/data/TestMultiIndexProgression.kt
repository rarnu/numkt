package com.rarnu.numkt.test.numkt.ndarray.data

import com.rarnu.numkt.ndarray.data.downTo
import com.rarnu.numkt.ndarray.data.rangeTo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

class TestMultiIndexProgression {

    @Test
    fun testOfIterating() {
        val prog = intArrayOf(0, 2, 1)..intArrayOf(2, 3, 2)
        val expected = mapOf(
            0 to intArrayOf(0, 2, 1),
            1 to intArrayOf(0, 2, 2),
            2 to intArrayOf(0, 3, 1),
            3 to intArrayOf(0, 3, 2),
            4 to intArrayOf(1, 2, 1),
            5 to intArrayOf(1, 2, 2),
            6 to intArrayOf(1, 3, 1),
            7 to intArrayOf(1, 3, 2),
            8 to intArrayOf(2, 2, 1),
            9 to intArrayOf(2, 2, 2),
            10 to intArrayOf(2, 3, 1),
            11 to intArrayOf(2, 3, 2),
        )
        val actual = mutableMapOf<Int, IntArray>()
        var count = 0
        for (v in prog) {
            actual[count++] = v.copyOf()
        }

        for (i in 0..11) {
            assertTrue(expected[i].contentEquals(actual[i]))
        }
    }

    @Test
    fun testToSingleIterating() {
        val pro = intArrayOf(0, 0, 0)..intArrayOf(0, 0, 0)
        var count = 0
        var actual: IntArray? = null
        for (i in pro) {
            actual = i
            count++
        }
        assertEquals(1, count)
        assertTrue(intArrayOf(0, 0, 0).contentEquals(actual))
    }

    @Test
    fun testToGetFirst() {
        val first = intArrayOf(0, 2, 1)
        val last = intArrayOf(2, 3, 2)
        val prog = first..last
        assertSame(first, prog.first)
    }

    @Test
    fun testToGetLast() {
        val first = intArrayOf(0, 2, 1)
        val last = intArrayOf(2, 3, 2)
        val prog = first..last
        assertSame(last, prog.last)
    }

    @Test
    fun testToGetStep() {
        val prog = intArrayOf(2, 3, 2) downTo intArrayOf(0, 2, 1)
        assertEquals(-1, prog.step)
    }

    @Test
    fun testReversed() {
        val prog = intArrayOf(0, 2, 1)..intArrayOf(2, 3, 2)
        val expected = mapOf(
            11 to intArrayOf(0, 2, 1),
            10 to intArrayOf(0, 2, 2),
            9 to intArrayOf(0, 3, 1),
            8 to intArrayOf(0, 3, 2),
            7 to intArrayOf(1, 2, 1),
            6 to intArrayOf(1, 2, 2),
            5 to intArrayOf(1, 3, 1),
            4 to intArrayOf(1, 3, 2),
            3 to intArrayOf(2, 2, 1),
            2 to intArrayOf(2, 2, 2),
            1 to intArrayOf(2, 3, 1),
            0 to intArrayOf(2, 3, 2),
        )
        val actual = mutableMapOf<Int, IntArray>()
        var count = 0
        for (v in prog.reverse) {
            actual[count++] = v.copyOf()
        }

        for (i in 0..11) {
            assertTrue(expected[i].contentEquals(actual[i]))
        }
    }
}