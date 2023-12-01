package com.rarnu.numkt.test.numkt.ndarray.data

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.zeros
import com.rarnu.numkt.ndarray.data.NDArray
import com.rarnu.numkt.ndarray.data.requireEqualShape
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class TestInternals {

    @Test
    fun requireEqualShapeThrowsExceptionForUnequalShape() {
        val left = Numkt.zeros<Double>(0, 1, 2, 3)
        val right = Numkt.zeros<Double>(0, 1, 2, 4)
        expectUnEqualShape(left, right)
    }

    @Test
    fun requireEqualShapeThrowsExceptionForDifferentNoOfDim() {
        val left = Numkt.zeros<Double>(0, 1, 2, 3)
        val right = Numkt.zeros<Double>(0, 1, 2)
        expectUnEqualShape(left, right)
    }

    @Test
    fun requireEqualShapeSucceedsForArraysWithEqualShapes() {
        val left = Numkt.zeros<Double>(0, 1, 2, 3)
        val right = Numkt.zeros<Double>(0, 1, 2, 3)
        requireEqualShape(left.shape, right.shape)
    }

    @Test
    fun requireEqualShapeSucceedsEmptyArrays() {
        val left = Numkt.zeros<Double>(0)
        val right = Numkt.zeros<Double>(0)
        assertTrue(left.isEmpty())
        assertTrue(right.isEmpty())
        requireEqualShape(left.shape, right.shape)
    }

    private fun expectUnEqualShape(left: NDArray<Double, *>, right: NDArray<Double, *>) {
        try {
            requireEqualShape(left.shape, right.shape)
            fail("Exception expected")
        } catch (_: IllegalArgumentException) {
        }
    }
}