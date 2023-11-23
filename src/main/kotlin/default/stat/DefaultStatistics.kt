package com.rarnu.numkt.default.stat

import com.rarnu.numkt.api.KEEngineType
import com.rarnu.numkt.api.NativeEngineType
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.default.DefaultEngineFactory
import com.rarnu.numkt.ndarray.data.*

object DefaultStatistics : Statistics {

    private val ktStat = DefaultEngineFactory.getEngine(KEEngineType).getStatistics()
    private val natStat = DefaultEngineFactory.getEngine(NativeEngineType).getStatistics()

    override fun <T : Number, D : Dimension> median(a: MultiArray<T, D>): Double? = natStat.median(a)

    override fun <T : Number, D : Dimension> average(a: MultiArray<T, D>, weights: MultiArray<T, D>?): Double = ktStat.average(a, weights)

    override fun <T : Number, D : Dimension> mean(a: MultiArray<T, D>): Double = ktStat.mean(a)

    override fun <T : Number, D : Dimension, O : Dimension> mean(a: MultiArray<T, D>, axis: Int): NDArray<Double, O> = ktStat.mean(a, axis)

    override fun <T : Number> meanD2(a: MultiArray<T, D2>, axis: Int): NDArray<Double, D1> = mean(a, axis)

    override fun <T : Number> meanD3(a: MultiArray<T, D3>, axis: Int): NDArray<Double, D2> = mean(a, axis)

    override fun <T : Number> meanD4(a: MultiArray<T, D4>, axis: Int): NDArray<Double, D3> = mean(a, axis)

    override fun <T : Number> meanDN(a: MultiArray<T, DN>, axis: Int): NDArray<Double, D4> = mean(a, axis)
}