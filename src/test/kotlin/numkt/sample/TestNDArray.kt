package sample

import com.rarnu.numkt.api.*
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.D3
import com.rarnu.numkt.ndarray.data.DN
import com.rarnu.numkt.ndarray.data.DataType
import org.junit.Assert.assertEquals
import org.junit.Test


class TestNDArray {
    @Test
    fun zerosD1() {
        val ndarray = Numkt.zeros<Int>(5)
        assertEquals("$ndarray", "[0, 0, 0, 0, 0]")
    }

    @Test
    fun zerosD2() {
        val ndarray = Numkt.zeros<Float>(2, 2)
        assertEquals("$ndarray", "[[0.0, 0.0],\n[0.0, 0.0]]")
    }

    @Test
    fun zerosD3() {
        val ndarray = Numkt.zeros<Double>(2, 2, 2)
        assertEquals("$ndarray", "[[[0.0, 0.0],\n[0.0, 0.0]],\n\n[[0.0, 0.0],\n[0.0, 0.0]]]")
    }

    @Test
    fun zerosD4() {
        val ndarray = Numkt.zeros<Double>(2, 1, 2, 1)
        assertEquals("$ndarray", "[[[[0.0],\n[0.0]]],\n\n\n[[[0.0],\n[0.0]]]]")
    }

    @Test
    fun zerosDN() {
        val ndarray = Numkt.zeros<Double>(1, 1, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[0.0, 0.0],\n[0.0, 0.0]]]]]")
    }

    @Test
    fun zerosDNWithDtype() {
        val dims = intArrayOf(3, 2)
        val ndarray = Numkt.zeros<Float, D2>(dims, DataType.FloatDataType)
        assertEquals("$ndarray", "[[0.0, 0.0],\n[0.0, 0.0],\n[0.0, 0.0]]")
    }

    @Test
    fun onesD1() {
        val ndarray = Numkt.ones<Int>(5)
        assertEquals("$ndarray", "[1, 1, 1, 1, 1]")
    }

    @Test
    fun onesD2() {
        val ndarray = Numkt.ones<Float>(2, 2)
        assertEquals("$ndarray", "[[1.0, 1.0],\n[1.0, 1.0]]")
    }

    @Test
    fun onesD3() {
        val ndarray = Numkt.ones<Double>(2, 2, 2)
        assertEquals("$ndarray", "[[[1.0, 1.0],\n[1.0, 1.0]],\n\n[[1.0, 1.0],\n[1.0, 1.0]]]")
    }

    @Test
    fun onesD4() {
        val ndarray = Numkt.ones<Double>(2, 1, 2, 1)
        assertEquals("$ndarray", "[[[[1.0],\n[1.0]]],\n\n\n[[[1.0],\n[1.0]]]]")
    }

    @Test
    fun onesDN() {
        val ndarray = Numkt.ones<Double>(1, 1, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1.0, 1.0],\n[1.0, 1.0]]]]]")
    }

    @Test
    fun onesDNWithDtype() {
        val dims = intArrayOf(3, 2)
        val ndarray = Numkt.ones<Float, D2>(dims, DataType.FloatDataType)
        assertEquals("$ndarray", "[[1.0, 1.0],\n[1.0, 1.0],\n[1.0, 1.0]]")
    }

    @Test
    fun identity() {
        val identNDArray = Numkt.identity<Long>(3)
        assertEquals("$identNDArray", "[[1, 0, 0],\n[0, 1, 0],\n[0, 0, 1]]")
    }

    @Test
    fun identityWithDtype() {
        val identNDArray = Numkt.identity<Long>(3, DataType.LongDataType)
        assertEquals("$identNDArray", "[[1, 0, 0],\n[0, 1, 0],\n[0, 0, 1]]")
    }

    @Test
    fun ndarray1D() {
        val ndarray = Numkt.ndarray(Numkt[1, 2, 3])
        assertEquals(ndarray.shape.joinToString(), "3")
        assertEquals(ndarray.dim.d, 1)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun ndarray2D() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[1, 2], Numkt[3, 4]])
        assertEquals(ndarray.shape.joinToString(), "2, 2")
        assertEquals(ndarray.dim.d, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarray3D() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[1, 2], Numkt[3, 4]], Numkt[Numkt[5, 6], Numkt[7, 8]]])
        assertEquals(ndarray.shape.joinToString(), "2, 2, 2")
        assertEquals(ndarray.dim.d, 3)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarray4D() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[Numkt[1, 2], Numkt[3, 4]], Numkt[Numkt[5, 6], Numkt[7, 8]]]])
        assertEquals(ndarray.shape.joinToString(), "1, 2, 2, 2")
        assertEquals(ndarray.dim.d, 4)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]]")
    }

    @Test
    fun ndarrayCollections() {
        val set = setOf(1, 2, 3, 4)
        val dims = intArrayOf(2, 1, 2)
        val ndarray = Numkt.ndarray<Int, D3>(set, dims)
        assertEquals(ndarray.shape.joinToString(), "2, 1, 2")
        assertEquals(ndarray.dim.d, 3)
        assertEquals("$ndarray", "[[[1, 2]],\n\n[[3, 4]]]")
    }

    @Test
    fun ndarrayCollectionsWithDim() {
        val set = setOf(1, 2, 3, 4)
        val dims = intArrayOf(2, 1, 2)
        val ndarray = Numkt.ndarray(set, dims, D3)
        assertEquals(ndarray.shape.joinToString(), "2, 1, 2")
        assertEquals(ndarray.dim.d, 3)
        assertEquals("$ndarray", "[[[1, 2]],\n\n[[3, 4]]]")
    }

    @Test
    fun ndarrayCollections1D() {
        val set = setOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(set)
        assertEquals(ndarray.shape.joinToString(), "4")
        assertEquals(ndarray.dim.d, 1)
        assertEquals("$ndarray", "[1, 2, 3, 4]")
    }

    @Test
    fun ndarrayCollections2D() {
        val set = setOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(set, 2, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarrayCollections3D() {
        val set = setOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(set, 2, 2, 2)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarrayCollections4D() {
        val set = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(set, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]],\n\n\n[[[9, 10],\n[11, 12]],\n\n[[13, 14],\n[15, 16]]]]")
    }

    @Test
    fun ndarrayCollectionsDN() {
        val set = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(set, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1, 2],\n[3, 4]]],\n\n\n[[[5, 6],\n[7, 8]]]],\n\n\n\n[[[[9, 10],\n[11, 12]]],\n\n\n[[[13, 14],\n[15, 16]]]]]")
    }

    @Test
    fun ndarrayByteArray1D() {
        val byteArray = byteArrayOf(1, 2, 3)
        val ndarray = Numkt.ndarray(byteArray)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun ndarrayByteArray2D() {
        val byteArray = byteArrayOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(byteArray, 2, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarrayByteArray3D() {
        val byteArray = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(byteArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarrayByteArray4D() {
        val byteArray = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(byteArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]],\n\n\n[[[9, 10],\n[11, 12]],\n\n[[13, 14],\n[15, 16]]]]")
    }

    @Test
    fun ndarrayByteArrayDN() {
        val byteArray = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(byteArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1, 2],\n[3, 4]]],\n\n\n[[[5, 6],\n[7, 8]]]],\n\n\n\n[[[[9, 10],\n[11, 12]]],\n\n\n[[[13, 14],\n[15, 16]]]]]")
    }

    @Test
    fun ndarrayShortArray1D() {
        val shortArray = shortArrayOf(1, 2, 3)
        val ndarray = Numkt.ndarray(shortArray)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun ndarrayShortArray2D() {
        val shortArray = shortArrayOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(shortArray, 2, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarrayShortArray3D() {
        val shortArray = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(shortArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarrayShortArray4D() {
        val shortArray = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(shortArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]],\n\n\n[[[9, 10],\n[11, 12]],\n\n[[13, 14],\n[15, 16]]]]")
    }

    @Test
    fun ndarrayShortArrayDN() {
        val shortArray = shortArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(shortArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1, 2],\n[3, 4]]],\n\n\n[[[5, 6],\n[7, 8]]]],\n\n\n\n[[[[9, 10],\n[11, 12]]],\n\n\n[[[13, 14],\n[15, 16]]]]]")
    }

    @Test
    fun ndarrayIntArray1D() {
        val intArray = intArrayOf(1, 2, 3)
        val ndarray = Numkt.ndarray(intArray)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun ndarrayIntArray2D() {
        val intArray = intArrayOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(intArray, 2, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarrayIntArray3D() {
        val intArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(intArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarrayIntArray4D() {
        val intArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(intArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]],\n\n\n[[[9, 10],\n[11, 12]],\n\n[[13, 14],\n[15, 16]]]]")
    }

    @Test
    fun ndarrayIntArrayDN() {
        val intArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(intArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1, 2],\n[3, 4]]],\n\n\n[[[5, 6],\n[7, 8]]]],\n\n\n\n[[[[9, 10],\n[11, 12]]],\n\n\n[[[13, 14],\n[15, 16]]]]]")
    }

    @Test
    fun ndarrayLongArray1D() {
        val longArray = longArrayOf(1, 2, 3)
        val ndarray = Numkt.ndarray(longArray)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun ndarrayLongArray2D() {
        val longArray = longArrayOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(longArray, 2, 2)
        assertEquals("$ndarray", "[[1, 2],\n[3, 4]]")
    }

    @Test
    fun ndarrayLongArray3D() {
        val longArray = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(longArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]]")
    }

    @Test
    fun ndarrayLongArray4D() {
        val longArray = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(longArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1, 2],\n[3, 4]],\n\n[[5, 6],\n[7, 8]]],\n\n\n[[[9, 10],\n[11, 12]],\n\n[[13, 14],\n[15, 16]]]]")
    }

    @Test
    fun ndarrayLongArrayDN() {
        val longArray = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val ndarray = Numkt.ndarray(longArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1, 2],\n[3, 4]]],\n\n\n[[[5, 6],\n[7, 8]]]],\n\n\n\n[[[[9, 10],\n[11, 12]]],\n\n\n[[[13, 14],\n[15, 16]]]]]")
    }

    @Test
    fun ndarrayFloatArray1D() {
        val floatArray = floatArrayOf(1f, 2f, 3f)
        val ndarray = Numkt.ndarray(floatArray)
        assertEquals("$ndarray", "[1.0, 2.0, 3.0]")
    }

    @Test
    fun ndarrayFloatArray2D() {
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f)
        val ndarray = Numkt.ndarray(floatArray, 2, 2)
        assertEquals("$ndarray", "[[1.0, 2.0],\n[3.0, 4.0]]")
    }

    @Test
    fun ndarrayFloatArray3D() {
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f)
        val ndarray = Numkt.ndarray(floatArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1.0, 2.0],\n[3.0, 4.0]],\n\n[[5.0, 6.0],\n[7.0, 8.0]]]")
    }

    @Test
    fun ndarrayFloatArray4D() {
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)
        val ndarray = Numkt.ndarray(floatArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1.0, 2.0],\n[3.0, 4.0]],\n\n[[5.0, 6.0],\n[7.0, 8.0]]],\n\n\n[[[9.0, 10.0],\n[11.0, 12.0]],\n\n[[13.0, 14.0],\n[15.0, 16.0]]]]")
    }

    @Test
    fun ndarrayFloatArrayDN() {
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)
        val ndarray = Numkt.ndarray(floatArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1.0, 2.0],\n[3.0, 4.0]]],\n\n\n[[[5.0, 6.0],\n[7.0, 8.0]]]],\n\n\n\n[[[[9.0, 10.0],\n[11.0, 12.0]]],\n\n\n[[[13.0, 14.0],\n[15.0, 16.0]]]]]")
    }

    @Test
    fun ndarrayDoubleArray1D() {
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0)
        val ndarray = Numkt.ndarray(doubleArray)
        assertEquals("$ndarray", "[1.0, 2.0, 3.0]")
    }

    @Test
    fun ndarrayDoubleArray2D() {
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
        val ndarray = Numkt.ndarray(doubleArray, 2, 2)
        assertEquals("$ndarray", "[[1.0, 2.0],\n[3.0, 4.0]]")
    }

    @Test
    fun ndarrayDoubleArray3D() {
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        val ndarray = Numkt.ndarray(doubleArray, 2, 2, 2)
        assertEquals("$ndarray", "[[[1.0, 2.0],\n[3.0, 4.0]],\n\n[[5.0, 6.0],\n[7.0, 8.0]]]")
    }

    @Test
    fun ndarrayDoubleArray4D() {
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0)
        val ndarray = Numkt.ndarray(doubleArray, 2, 2, 2, 2)
        assertEquals("$ndarray", "[[[[1.0, 2.0],\n[3.0, 4.0]],\n\n[[5.0, 6.0],\n[7.0, 8.0]]],\n\n\n[[[9.0, 10.0],\n[11.0, 12.0]],\n\n[[13.0, 14.0],\n[15.0, 16.0]]]]")
    }

    @Test
    fun ndarrayDoubleArrayDN() {
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0)
        val ndarray = Numkt.ndarray(doubleArray, 2, 2, 1, 2, 2)
        assertEquals("$ndarray", "[[[[[1.0, 2.0],\n[3.0, 4.0]]],\n\n\n[[[5.0, 6.0],\n[7.0, 8.0]]]],\n\n\n\n[[[[9.0, 10.0],\n[11.0, 12.0]]],\n\n\n[[[13.0, 14.0],\n[15.0, 16.0]]]]]")
    }

    @Test
    fun d1array() {
        val ndarray = Numkt.d1array(3) { -it }
        assertEquals("$ndarray", "[0, -1, -2]")
    }

    @Test
    fun d2array() {
        val ndarray = Numkt.d2array(2, 2) { it * it }
        assertEquals("$ndarray", "[[0, 1],\n[4, 9]]")
    }

    @Test
    fun d2arrayIndices() {
        val ndarray = Numkt.d2arrayIndices(2, 2) { i, j -> i + j }
        assertEquals("$ndarray", "[[0, 1],\n[1, 2]]")
    }

    @Test
    fun d3array() {
        val ndarray = Numkt.d3array(2, 2, 2) { if (it % 2 == 0) 0 else it * it }
        assertEquals("$ndarray", "[[[0, 1],\n[0, 9]],\n\n[[0, 25],\n[0, 49]]]")
    }

    @Test
    fun d3arrayIndices() {
        val ndarray = Numkt.d3arrayIndices(2, 2, 2) { i, j, k -> i + j + k }
        assertEquals("$ndarray", "[[[0, 1],\n[1, 2]],\n\n[[1, 2],\n[2, 3]]]")
    }

    @Test
    fun d4array() {
        val ndarray = Numkt.d4array(2, 1, 2, 1) { (it - 10f) / 10 }
        assertEquals("$ndarray", "[[[[-1.0],\n[-0.9]]],\n\n\n[[[-0.8],\n[-0.7]]]]")
    }

    @Test
    fun d4arrayIndices() {
        val ndarray = Numkt.d4arrayIndices(2, 1, 2, 1) { i, j, k, m ->
            (i - j * 5f) / (k + 1) + m
        }
        assertEquals("$ndarray", "[[[[0.0],\n[0.0]]],\n\n\n[[[1.0],\n[0.5]]]]")
    }

    @Test
    fun dnarray() {
        val ndarray = Numkt.dnarray(1, 2, 1, 2, 1, 2) { kotlin.math.PI * it }
        assertEquals("$ndarray", "[[[[[[0.0, 3.141592653589793]],\n\n[[6.283185307179586, 9.42477796076938]]]],\n\n\n\n[[[[12.566370614359172, 15.707963267948966]],\n\n[[18.84955592153876, 21.991148575128552]]]]]]")
    }

    @Test
    fun dnarrayWithDims() {
        val dims = intArrayOf(1, 1, 2, 1, 1)
        val ndarray = Numkt.dnarray<Double, DN>(dims) { kotlin.math.PI * it }
        assertEquals("$ndarray", "[[[[[0.0]],\n\n[[3.141592653589793]]]]]")
    }

    @Test
    fun ndarrayOf() {
        val ndarray = Numkt.ndarrayOf(1, 2, 3)
        assertEquals("$ndarray", "[1, 2, 3]")
    }

    @Test
    fun arange() {
        val ndarray = Numkt.arange<Float>(start = 2, stop = 5)
        val ndarrayWithStep = Numkt.arange<Int>(2, 7, 2)
        assertEquals("$ndarray", "[2.0, 3.0, 4.0]")
        assertEquals("$ndarrayWithStep", "[2, 4, 6]")
    }

    @Test
    fun arangeDoubleStep() {
        val ndarray = Numkt.arange<Float>(1, 7, 1.3)
        assertEquals("$ndarray", "[1.0, 2.3, 3.6, 4.9, 6.2]")
    }

    @Test
    fun arangeWithoutStart() {
        val ndarray = Numkt.arange<Int>(10)
        val ndarrayStep = Numkt.arange<Long>(10, 2)
        assertEquals("$ndarray", "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
        assertEquals("$ndarrayStep", "[0, 2, 4, 6, 8]")
    }

    @Test
    fun arangeDoubleStepWithoutStart() {
        val ndarray = Numkt.arange<Double>(5, 0.5)
        assertEquals("$ndarray", "[0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5]")
    }

    @Test
    fun linspace() {
        val ndarray = Numkt.linspace<Float>(-1, 1, 9)
        assertEquals("$ndarray", "[-1.0, -0.75, -0.5, -0.25, 0.0, 0.25, 0.5, 0.75, 1.0]")
    }

    @Test
    fun linspaceDouble() {
        val ndarray = Numkt.linspace<Double>(-1.5, 2.1, 7)
        assertEquals("$ndarray", "[-1.5, -0.9, -0.30000000000000004, 0.2999999999999998, 0.8999999999999999, 1.5, 2.1]")
    }

    @Test
    fun toNDArray() {
        val list = listOf(1, 2, 3, 4)
        val ndarray = list.toNDArray()
        assertEquals("$ndarray", "[1, 2, 3, 4]")
    }
}