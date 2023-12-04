@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY", "DuplicatedCode")

package com.rarnu.numkt.native.math

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.math.Math
import com.rarnu.numkt.api.math.MathEx
import com.rarnu.numkt.api.zeros
import com.rarnu.numkt.kotlin.math.remove
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.complex.map
import com.rarnu.numkt.ndarray.data.*
import com.rarnu.numkt.ndarray.operations.plusAssign

internal object NativeMath : Math {

    override val mathEx: MathEx get() = NativeMathEx

    override fun <T : Number, D : Dimension> argMax(a: MultiArray<T, D>): Int {
        val strides: IntArray? = if (a.consistent) null else a.strides
        return JniMath.argMax(a.data.data, a.offset, a.size, a.shape, strides, a.dtype.nativeCode)
    }

    override fun <T : Number, D : Dimension, O : Dimension> argMax(a: MultiArray<T, D>, axis: Int): NDArray<Int, O> {
        require(a.dim.d > 1) { "NDArray of dimension one, use the `argMax` function without axis." }
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val newShape = a.shape.remove(axis)
        val min = min(a)
        val size = newShape.fold(1, Int::times)
        val maxArray = MutableList(size) { min }
        val argMaxData = initMemoryView<Int>(size, DataType.IntDataType)
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 0 until a.shape[axis]) {
            indexMap[axis] = index.r
            val t = a.slice<T, D, O>(indexMap)
            var count = 0
            for (el in t) {
                if (maxArray[count] < el) {
                    argMaxData[count] = index
                    maxArray[count] = el
                }
                count++
            }
        }
        return NDArray(argMaxData, 0, newShape, dim = dimensionOf(newShape.size))
    }

    override fun <T : Number> argMaxD2(a: MultiArray<T, D2>, axis: Int): NDArray<Int, D1> = argMax(a, axis)

    override fun <T : Number> argMaxD3(a: MultiArray<T, D3>, axis: Int): NDArray<Int, D2> = argMax(a, axis)

    override fun <T : Number> argMaxD4(a: MultiArray<T, D4>, axis: Int): NDArray<Int, D3> = argMax(a, axis)

    override fun <T : Number> argMaxDN(a: MultiArray<T, DN>, axis: Int): NDArray<Int, DN> = argMax(a, axis)

    override fun <T : Number, D : Dimension> argMin(a: MultiArray<T, D>): Int {
        val strides: IntArray? = if (a.consistent) null else a.strides
        return JniMath.argMin(a.data.data, a.offset, a.size, a.shape, strides, a.dtype.nativeCode)
    }

    override fun <T : Number, D : Dimension, O : Dimension> argMin(a: MultiArray<T, D>, axis: Int): NDArray<Int, O> {
        require(a.dim.d > 1) { "NDArray of dimension one, use the `argMin` function without axis." }
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val newShape = a.shape.remove(axis)
        val max = max(a)
        val size = newShape.fold(1, Int::times)
        val minArray = MutableList(size) { max }
        val argMinData = initMemoryView<Int>(size, DataType.IntDataType)
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 0 until a.shape[axis]) {
            indexMap[axis] = index.r
            val t = a.slice<T, D, O>(indexMap)
            var count = 0
            for (el in t) {
                if (minArray[count] > el) {
                    argMinData[count] = index
                    minArray[count] = el
                }
                count++
            }
        }
        return NDArray(argMinData, 0, newShape, dim = dimensionOf(newShape.size))
    }

    override fun <T : Number> argMinD2(a: MultiArray<T, D2>, axis: Int): NDArray<Int, D1> = argMin(a, axis)

    override fun <T : Number> argMinD3(a: MultiArray<T, D3>, axis: Int): NDArray<Int, D2> = argMin(a, axis)

    override fun <T : Number> argMinD4(a: MultiArray<T, D4>, axis: Int): NDArray<Int, D3> = argMin(a, axis)

    override fun <T : Number> argMinDN(a: MultiArray<T, DN>, axis: Int): NDArray<Int, DN> = argMin(a, axis)

    override fun <T : Number, D : Dimension> max(a: MultiArray<T, D>): T {
        val strides: IntArray? = if (a.consistent) null else a.strides
        return when (a.dtype) {
            DataType.ByteDataType -> JniMath.arrayMax(a.data.getByteArray(), a.offset, a.size, a.shape, strides)
            DataType.ShortDataType -> JniMath.arrayMax(a.data.getShortArray(), a.offset, a.size, a.shape, strides)
            DataType.IntDataType -> JniMath.arrayMax(a.data.getIntArray(), a.offset, a.size, a.shape, strides)
            DataType.LongDataType -> JniMath.arrayMax(a.data.getLongArray(), a.offset, a.size, a.shape, strides)
            DataType.FloatDataType -> JniMath.arrayMax(a.data.getFloatArray(), a.offset, a.size, a.shape, strides)
            DataType.DoubleDataType -> JniMath.arrayMax(a.data.getDoubleArray(), a.offset, a.size, a.shape, strides)
            else -> throw IllegalArgumentException("ComplexFloat and ComplexDouble cannot be compared.")
        } as T
    }

    override fun <T : Number, D : Dimension, O : Dimension> max(a: MultiArray<T, D>, axis: Int): NDArray<T, O> {
        require(a.dim.d > 1) { "NDArray of dimension one, use the `max` function without axis." }
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val newShape = a.shape.remove(axis)
        val min = min(a)
        val size = newShape.fold(1, Int::times)
        val maxData = initMemoryView(size, a.dtype) { min }
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 0 until a.shape[axis]) {
            indexMap[axis] = index.r
            val t = a.slice<T, D, O>(indexMap)
            var count = 0
            for (el in t) {
                if (maxData[count] < el) maxData[count] = el
                count++
            }
        }
        return NDArray(maxData, 0, newShape, dim = dimensionOf(newShape.size))
    }

    override fun <T : Number> maxD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1> = max(a, axis)

    override fun <T : Number> maxD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2> = max(a, axis)

    override fun <T : Number> maxD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3> = max(a, axis)

    override fun <T : Number> maxDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN> = max(a, axis)

    override fun <T : Number, D : Dimension> min(a: MultiArray<T, D>): T {
        val strides: IntArray? = if (a.consistent) null else a.strides
        return when (a.dtype) {
            DataType.ByteDataType -> JniMath.arrayMin(a.data.getByteArray(), a.offset, a.size, a.shape, strides)
            DataType.ShortDataType -> JniMath.arrayMin(a.data.getShortArray(), a.offset, a.size, a.shape, strides)
            DataType.IntDataType -> JniMath.arrayMin(a.data.getIntArray(), a.offset, a.size, a.shape, strides)
            DataType.LongDataType -> JniMath.arrayMin(a.data.getLongArray(), a.offset, a.size, a.shape, strides)
            DataType.FloatDataType -> JniMath.arrayMin(a.data.getFloatArray(), a.offset, a.size, a.shape, strides)
            DataType.DoubleDataType -> JniMath.arrayMin(a.data.getDoubleArray(), a.offset, a.size, a.shape, strides)
            else -> throw IllegalArgumentException("ComplexFloat and ComplexDouble cannot be compared.")
        } as T
    }

    override fun <T : Number, D : Dimension, O : Dimension> min(a: MultiArray<T, D>, axis: Int): NDArray<T, O> {
        require(a.dim.d > 1) { "NDArray of dimension one, use the `min` function without axis." }
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val newShape = a.shape.remove(axis)
        val max = max(a)
        val size = newShape.fold(1, Int::times)
        val minData = initMemoryView(size, a.dtype) { max }
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 0 until a.shape[axis]) {
            indexMap[axis] = index.r
            val t = a.slice<T, D, O>(indexMap)
            var count = 0
            for (el in t) {
                if (minData[count] > el) minData[count] = el
                count++
            }
        }
        return NDArray(minData, 0, newShape, dim = dimensionOf(newShape.size))
    }

    override fun <T : Number> minD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1> = min(a, axis)

    override fun <T : Number> minD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2> = min(a, axis)

    override fun <T : Number> minD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3> = min(a, axis)

    override fun <T : Number> minDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN> = min(a, axis)

    override fun <T : Number, D : Dimension> sum(a: MultiArray<T, D>): T {
        val strides: IntArray? = if (a.consistent) null else a.strides
        return when (a.dtype) {
            DataType.ByteDataType -> JniMath.sum(a.data.getByteArray(), a.offset, a.size, a.shape, strides)
            DataType.ShortDataType -> JniMath.sum(a.data.getShortArray(), a.offset, a.size, a.shape, strides)
            DataType.IntDataType -> JniMath.sum(a.data.getIntArray(), a.offset, a.size, a.shape, strides)
            DataType.LongDataType -> JniMath.sum(a.data.getLongArray(), a.offset, a.size, a.shape, strides)
            DataType.FloatDataType -> JniMath.sum(a.data.getFloatArray(), a.offset, a.size, a.shape, strides)
            DataType.DoubleDataType -> JniMath.sum(a.data.getDoubleArray(), a.offset, a.size, a.shape, strides)
            DataType.ComplexFloatDataType -> {
                val p = a.data.getComplexFloatArray().map { it.re to it.im }
                val sumRe = JniMath.sum(p.map { it.first }.toFloatArray(), a.offset, a.size, a.shape, strides)
                val sumIm = JniMath.sum(p.map { it.second }.toFloatArray(), a.offset, a.size, a.shape, strides)
                ComplexFloat(sumRe, sumIm)
            }
            DataType.ComplexDoubleDataType -> {
                val p = a.data.getComplexDoubleArray().map { it.re to it.im }
                val sumRe = JniMath.sum(p.map { it.first }.toDoubleArray(), a.offset, a.size, a.shape, strides)
                val sumIm = JniMath.sum(p.map { it.second }.toDoubleArray(), a.offset, a.size, a.shape, strides)
                ComplexDouble(sumRe, sumIm)
            }
        } as T
    }

    override fun <T : Number, D : Dimension, O : Dimension> sum(a: MultiArray<T, D>, axis: Int): NDArray<T, O> {
        require(a.dim.d > 1) { "NDArray of dimension one, use the `sum` function without axis." }
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val newShape = a.shape.remove(axis)
        val ret: NDArray<T, O> = Numkt.zeros(newShape, a.dtype)
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 0 until a.shape[axis]) {
            indexMap[axis] = index.r
            ret += a.slice(indexMap)
        }
        return ret
    }

    override fun <T : Number> sumD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1> = sum(a, axis)

    override fun <T : Number> sumD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2> = sum(a, axis)

    override fun <T : Number> sumD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3> = sum(a, axis)

    override fun <T : Number> sumDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN> = sum(a, axis)

    override fun <T : Number, D : Dimension> cumSum(a: MultiArray<T, D>): D1Array<T> {
        val ret = D1Array<T>(initMemoryView(a.size, a.dtype), shape = intArrayOf(a.size), dim = D1)
        val strides: IntArray? = if (a.consistent) null else a.strides
        when (a.dtype) {
            DataType.ByteDataType -> JniMath.cumSum(a.data.getByteArray(), a.offset, a.size, a.shape, strides, ret.data.getByteArray())
            DataType.ShortDataType -> JniMath.cumSum(a.data.getShortArray(), a.offset, a.size, a.shape, strides, ret.data.getShortArray())
            DataType.IntDataType -> JniMath.cumSum(a.data.getIntArray(), a.offset, a.size, a.shape, strides, ret.data.getIntArray())
            DataType.LongDataType -> JniMath.cumSum(a.data.getLongArray(), a.offset, a.size, a.shape, strides, ret.data.getLongArray())
            DataType.FloatDataType -> JniMath.cumSum(a.data.getFloatArray(), a.offset, a.size, a.shape, strides, ret.data.getFloatArray())
            DataType.DoubleDataType -> JniMath.cumSum(a.data.getDoubleArray(), a.offset, a.size, a.shape, strides, ret.data.getDoubleArray())
            DataType.ComplexFloatDataType -> {
                val p = a.data.getComplexFloatArray().map { it.re to it.im }
                val fRe = p.map { it.first }.toFloatArray()
                val fIm = p.map { it.second }.toFloatArray()
                JniMath.cumSum(fRe, a.offset, a.size, a.shape, strides, fRe)
                JniMath.cumSum(fIm, a.offset, a.size, a.shape, strides, fIm)
            }
            DataType.ComplexDoubleDataType -> {
                val p = a.data.getComplexDoubleArray().map { it.re to it.im }
                val dRe = p.map { it.first }.toDoubleArray()
                val dIm = p.map { it.second }.toDoubleArray()
                JniMath.cumSum(dRe, a.offset, a.size, a.shape, strides, dRe)
                JniMath.cumSum(dIm, a.offset, a.size, a.shape, strides, dIm)
            }
        }
        return ret
    }

    override fun <T : Number, D : Dimension> cumSum(a: MultiArray<T, D>, axis: Int): NDArray<T, D> {
        require(axis in 0 until a.dim.d) { "axis $axis is out of bounds for this ndarray of dimension ${a.dim.d}." }
        val ret: NDArray<T, D> = (a as NDArray<T, D>).deepCopy()
        val indexMap: MutableMap<Int, Indexing> = mutableMapOf()
        for (i in a.shape.indices) {
            if (i == axis) continue
            indexMap[i] = 0.r until a.shape[i]
        }
        for (index in 1 until a.shape[axis]) {
            indexMap[axis] = index.r
            val tmp = ret.slice<T, D, DN>(indexMap)
            indexMap[axis] = index.r - 1.r
            tmp += ret.slice(indexMap)
        }
        return ret
    }
}
