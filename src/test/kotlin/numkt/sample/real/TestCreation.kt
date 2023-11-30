package com.rarnu.numkt.test.numkt.sample.real

import com.rarnu.numkt.api.*
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.complex.i
import com.rarnu.numkt.ndarray.complex.plus
import com.rarnu.numkt.ndarray.data.DataType
import com.rarnu.numkt.ndarray.operations.toList
import org.junit.Assert.assertEquals
import org.junit.Test

class TestCreation {

    @Test
    fun simpleWayOfCreation() {
        val a = Numkt.ndarray(Numkt[1, 2, 3])
        val b = Numkt.ndarray(Numkt[Numkt[1.5, 5.8], Numkt[9.1, 7.3]])

        assertEquals(DataType.IntDataType, a.dtype)
        assertEquals(1, a.dim.d)
        assertEquals(DataType.DoubleDataType, b.dtype)
        assertEquals(2, b.dim.d)
    }

    @Test
    fun createArrayFromCollections() {
        val a = Numkt.ndarray(setOf(1, 2, 3))
        val b = listOf(8.4, 5.2, 9.3, 11.5).toNDArray()
        assertEquals(a.toList(), listOf(1, 2, 3))
        assertEquals(b.toList(), listOf(8.4, 5.2, 9.3, 11.5))
    }

    @Test
    fun createArrayFromPrimitiveWithShape() {
        val a = Numkt.ndarray(floatArrayOf(34.2f, 13.4f, 4.8f, 8.8f, 3.3f, 7.1f), 2, 1, 3)
        assertEquals(listOf(2, 1, 3), a.shape.toList())
        assertEquals(listOf(34.2f, 13.4f, 4.8f, 8.8f, 3.3f, 7.1f), a.toList())
    }

    @Test
    fun createZerosAndOnesArrays() {
        val a = Numkt.zeros<Int>(7)
        val b = Numkt.ones<Float>(3, 2)
        assertEquals(a.toList(), listOf(0, 0, 0, 0, 0, 0, 0))
        assertEquals(b.toList(), listOf(1f, 1f, 1f, 1f, 1f, 1f))
    }

    @Test
    fun creationWithLambda() {
        val a = Numkt.d3array(2, 2, 3) { it * it } // create an array of dimension 3
        val b = Numkt.d2arrayIndices(3, 3) { i, j -> ComplexFloat(i, j) }
        assertEquals(a.toList(), listOf(0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121))
        assertEquals(b.toList(), listOf(0f + 0f.i, 0f + 1f.i, 0f + 2f.i, 1f + 0f.i, 1f + 1f.i, 1f + 2f.i, 2f + 0f.i, 2f + 1f.i, 2f + 2f.i))
    }

    @Test
    fun creationWithArangeAndLinspace() {
        val a = Numkt.arange<Int>(3, 10, 2)
        val b = Numkt.linspace<Double>(0.0, 10.0, 8)
        assertEquals(a.size, 4)
        assertEquals(b.size, 8)
    }
}