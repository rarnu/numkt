package com.rarnu.numkt.native.stat

import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.ndarray.data.*
import com.rarnu.numkt.ndarray.operations.first
import com.rarnu.numkt.ndarray.operations.times
import com.rarnu.numkt.native.math.NativeMath

internal object NativeStatistics : Statistics {

    override fun <T : Number, D : Dimension> median(a: MultiArray<T, D>): Double? {
        val size = a.size
        return when {
            size == 1 -> a.first().toDouble()
            size > 1 -> JniStat.median(a.deepCopy().data.data, size, a.dtype.nativeCode)
            else -> null
        }
    }

    override fun <T : Number, D : Dimension> average(a: MultiArray<T, D>, weights: MultiArray<T, D>?): Double {
        if (weights == null) return mean(a)
        return NativeMath.sum(a * weights).toDouble() / NativeMath.sum(weights).toDouble()
    }

    override fun <T : Number, D : Dimension> mean(a: MultiArray<T, D>): Double {
        val ret = NativeMath.sum(a)
        return ret.toDouble() / a.size
    }

    override fun <T : Number, D : Dimension, O : Dimension> mean(a: MultiArray<T, D>, axis: Int): NDArray<Double, O> {
        TODO("Not yet implemented")
    }

    override fun <T : Number> meanD2(a: MultiArray<T, D2>, axis: Int): NDArray<Double, D1> {
        TODO("Not yet implemented")
    }

    override fun <T : Number> meanD3(a: MultiArray<T, D3>, axis: Int): NDArray<Double, D2> {
        TODO("Not yet implemented")
    }

    override fun <T : Number> meanD4(a: MultiArray<T, D4>, axis: Int): NDArray<Double, D3> {
        TODO("Not yet implemented")
    }

    override fun <T : Number> meanDN(a: MultiArray<T, DN>, axis: Int): NDArray<Double, D4> {
        TODO("Not yet implemented")
    }
}