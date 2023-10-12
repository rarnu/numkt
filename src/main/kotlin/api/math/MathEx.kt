package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

/**
 * Extension interface for [Math] for improved type support.
 */
interface MathEx {
    /**
     * Returns a ndarray of Double from the given ndarray to each element of which an exp function has been applied.
     */
    fun <T : Number, D : Dimension> exp(a: MultiArray<T, D>): NDArray<Double, D>

    /**
     * Returns a ndarray of Float from the given ndarray to each element of which an exp function has been applied.
     */
    fun <D : Dimension> expF(a: MultiArray<Float, D>): NDArray<Float, D>

    /**
     * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which an exp function has been applied.
     */
    fun <D : Dimension> expCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D>

    /**
     * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which an exp function has been applied.
     */
    fun <D : Dimension> expCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D>

    /**
     * Returns a ndarray of Double from the given ndarray to each element of which a log function has been applied.
     */
    fun <T : Number, D : Dimension> log(a: MultiArray<T, D>): NDArray<Double, D>

    /**
     * Returns a ndarray of Float from the given ndarray to each element of which a log function has been applied.
     */
    fun <D : Dimension> logF(a: MultiArray<Float, D>): NDArray<Float, D>

    /**
     * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a log function has been applied.
     */
    fun <D : Dimension> logCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D>

    /**
     * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a log function has been applied.
     */
    fun <D : Dimension> logCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D>

    /**
     * Returns a ndarray of Double from the given ndarray to each element of which a sin function has been applied.
     */
    fun <T : Number, D : Dimension> sin(a: MultiArray<T, D>): NDArray<Double, D>

    /**
     * Returns a ndarray of Float from the given ndarray to each element of which a sin function has been applied.
     */
    fun <D : Dimension> sinF(a: MultiArray<Float, D>): NDArray<Float, D>

    /**
     * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a sin function has been applied.
     */
    fun <D : Dimension> sinCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D>

    /**
     * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a sin function has been applied.
     */
    fun <D : Dimension> sinCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D>

    /**
     * Returns a ndarray of Double from the given ndarray to each element of which a cos function has been applied.
     */
    fun <T : Number, D : Dimension> cos(a: MultiArray<T, D>): NDArray<Double, D>

    /**
     * Returns a ndarray of Float from the given ndarray to each element of which a cos function has been applied.
     */
    fun <D : Dimension> cosF(a: MultiArray<Float, D>): NDArray<Float, D>

    /**
     * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a cos function has been applied.
     */
    fun <D : Dimension> cosCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D>

    /**
     * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a cos function has been applied.
     */
    fun <D : Dimension> cosCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D>
}