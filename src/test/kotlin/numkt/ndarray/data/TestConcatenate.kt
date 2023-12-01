package com.rarnu.numkt.test.numkt.ndarray.data

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.zeros
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.data.Slice
import com.rarnu.numkt.ndarray.data.get
import com.rarnu.numkt.ndarray.operations.asSequence
import com.rarnu.numkt.ndarray.operations.plus
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse

class TestConcatenate {
    @Test
    fun concatenateShouldConcatenateForSimpleArray() {

        val arr1 = Numkt.zeros<Double>(1) + 1.0
        val arr2 = Numkt.zeros<Double>(2) + 2.0
        val arr3 = Numkt.zeros<Double>(3) + 3.0
        val arr4 = Numkt.zeros<Double>(4) + 4.0

        val result = arr1.cat(listOf(arr2, arr3, arr4), 0)

        assertContentEquals(doubleArrayOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0).toTypedArray(), result.data.getDoubleArray().toTypedArray())

    }

    @Test
    fun concatenateShouldConcatenateForNonConsistentArray() {

        val arr1 = Numkt.zeros<Double>(1) + 1.0
        val arr2 = Numkt.zeros<Double>(2) + 2.0
        val arr3 = Numkt.zeros<Double>(3) + 3.0
        val arr4 = Numkt.zeros<Double>(10) + 4.0
        val arr5 = arr4[Slice(2, 5, 1)]

        assertFalse(arr5.consistent)
        val result = arr1.cat(listOf(arr2, arr3, arr5), 0)

        assertContentEquals(doubleArrayOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0).toTypedArray(), result.data.getDoubleArray().toTypedArray())

    }

    @Test
    fun concatenateShouldConcatenateForComplex() {

        val arr1 = Numkt.zeros<ComplexDouble>(1) + ComplexDouble(1.0, 0.0)
        val arr2 = Numkt.zeros<ComplexDouble>(2) + ComplexDouble(2.0, 0.0)
        val arr3 = Numkt.zeros<ComplexDouble>(3) + ComplexDouble(3.0, 0.0)
        val arr4 = Numkt.zeros<ComplexDouble>(4) + ComplexDouble(4.0, 0.0)

        val result = arr1.cat(listOf(arr2, arr3, arr4), 0)

        val realResult = result.asSequence().map { it.re }.toList().toDoubleArray()

        assertContentEquals(doubleArrayOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0).toTypedArray(), realResult.toTypedArray())

    }
}
