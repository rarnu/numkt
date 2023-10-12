package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.data.*

/**
 * Mathematical methods interface.
 */
interface Math {

    /**
     * instance of [MathEx]
     */
    val mathEx: MathEx

    /**
     * Returns flat index of maximum element in a ndarray.
     */
    fun <T : Number, D : Dimension> argMax(a: MultiArray<T, D>): Int

    /**
     * Returns a ndarray of indices of maximum elements in a ndarray [a] over a given [axis].
     */
    fun <T : Number, D : Dimension, O : Dimension> argMax(a: MultiArray<T, D>, axis: Int): NDArray<Int, O>

    /**
     * Returns a ndarray of indices of maximum elements in a two-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMaxD2(a: MultiArray<T, D2>, axis: Int): NDArray<Int, D1>

    /**
     * Returns a ndarray of indices of maximum elements in a three-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMaxD3(a: MultiArray<T, D3>, axis: Int): NDArray<Int, D2>

    /**
     * Returns a ndarray of indices of maximum elements in a four-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMaxD4(a: MultiArray<T, D4>, axis: Int): NDArray<Int, D3>

    /**
     * Returns a ndarray of indices of maximum elements in an n-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMaxDN(a: MultiArray<T, DN>, axis: Int): NDArray<Int, DN>

    /**
     * Returns flat index of minimum element in a ndarray.
     */
    fun <T : Number, D : Dimension> argMin(a: MultiArray<T, D>): Int

    /**
     * Returns a ndarray of indices of minimum elements in a ndarray [a] over a given [axis].
     */
    fun <T : Number, D : Dimension, O : Dimension> argMin(a: MultiArray<T, D>, axis: Int): NDArray<Int, O>

    /**
     * Returns a ndarray of indices of minimum elements in a two-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMinD2(a: MultiArray<T, D2>, axis: Int): NDArray<Int, D1>

    /**
     * Returns a ndarray of indices of minimum elements in a three-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMinD3(a: MultiArray<T, D3>, axis: Int): NDArray<Int, D2>

    /**
     * Returns a ndarray of indices of minimum elements in a four-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMinD4(a: MultiArray<T, D4>, axis: Int): NDArray<Int, D3>

    /**
     * Returns a ndarray of indices of minimum elements in an n-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> argMinDN(a: MultiArray<T, DN>, axis: Int): NDArray<Int, DN>

    /**
     * Returns maximum element of the given ndarray.
     */
    fun <T : Number, D : Dimension> max(a: MultiArray<T, D>): T

    /**
     * Returns maximum of a ndarray [a] along a given [axis].
     */
    fun <T : Number, D : Dimension, O : Dimension> max(a: MultiArray<T, D>, axis: Int): NDArray<T, O>

    /**
     * Returns maximum of a two-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> maxD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1>

    /**
     * Returns maximum of a three-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> maxD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2>

    /**
     * Returns maximum of a four-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> maxD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3>

    /**
     * Returns maximum of an n-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> maxDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN>

    /**
     * Returns minimum element of the given ndarray.
     */
    fun <T : Number, D : Dimension> min(a: MultiArray<T, D>): T

    /**
     * Returns minimum of a ndarray [a] along a given [axis].
     */
    fun <T : Number, D : Dimension, O : Dimension> min(a: MultiArray<T, D>, axis: Int): NDArray<T, O>

    /**
     * Returns minimum of a two-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> minD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1>

    /**
     * Returns minimum of a three-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> minD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2>

    /**
     * Returns minimum of a four-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> minD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3>

    /**
     * Returns minimum of an n-dimensional ndarray [a] along a given [axis].
     */
    fun <T : Number> minDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN>

    /**
     * Returns sum of all elements in the given ndarray.
     */
    fun <T : Number, D : Dimension> sum(a: MultiArray<T, D>): T

    /**
     * Returns a ndarray of sum all elements over a given [axis].
     */
    fun <T : Number, D : Dimension, O : Dimension> sum(a: MultiArray<T, D>, axis: Int): NDArray<T, O>

    /**
     * Returns a ndarray of sum all elements in a two-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> sumD2(a: MultiArray<T, D2>, axis: Int): NDArray<T, D1>

    /**
     * Returns a ndarray of sum all elements in a three-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> sumD3(a: MultiArray<T, D3>, axis: Int): NDArray<T, D2>

    /**
     * Returns a ndarray of sum all elements in a four-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> sumD4(a: MultiArray<T, D4>, axis: Int): NDArray<T, D3>

    /**
     * Returns a ndarray of sum all elements in an n-dimensional ndarray [a] over a given [axis].
     */
    fun <T : Number> sumDN(a: MultiArray<T, DN>, axis: Int): NDArray<T, DN>

    /**
     * Returns cumulative sum of all elements in the given ndarray.
     */
    fun <T : Number, D : Dimension> cumSum(a: MultiArray<T, D>): D1Array<T>

    /**
     * Returns cumulative sum of all elements in the given ndarray along the given [axis].
     */
    fun <T : Number, D : Dimension> cumSum(a: MultiArray<T, D>, axis: Int): NDArray<T, D>
}