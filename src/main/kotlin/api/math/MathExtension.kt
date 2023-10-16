package com.rarnu.numkt.api.math

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.D1Array
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns flat index of maximum element in a ndarray.
 *
 * same as [Math.argMax]
 */
fun <T : Number, D : Dimension> MultiArray<T, D>.argMax(): Int = Numkt.math.argMax(this)

/**
 * Returns flat index of minimum element in a ndarray.
 *
 * same as [Math.argMin]
 */
fun <T : Number, D : Dimension> MultiArray<T, D>.argMin(): Int = Numkt.math.argMin(this)

/**
 * Returns a ndarray of Double from the given ndarray to each element of which an exp function has been applied.
 *
 * same as [Math.exp]
 */
@JvmName("expTD")
fun <T : Number, D : Dimension> MultiArray<T, D>.exp(): NDArray<Double, D> = Numkt.math.exp(this)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which an exp function has been applied.
 *
 * same as [Math.exp]
 */
@JvmName("expFloatD")
fun <D : Dimension> MultiArray<Float, D>.exp(): NDArray<Float, D> = Numkt.math.mathEx.expF(this)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which an exp function has been applied.
 *
 * same as [Math.exp]
 */
@JvmName("expComplexFloatD")
fun <D : Dimension> MultiArray<ComplexFloat, D>.exp(): NDArray<ComplexFloat, D> = Numkt.math.mathEx.expCF(this)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which an exp function has been applied.
 *
 * same as [Math.exp]
 */
@JvmName("expComplexDoubleD")
fun <D : Dimension> MultiArray<ComplexDouble, D>.exp(): NDArray<ComplexDouble, D> = Numkt.math.mathEx.expCD(this)

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a log function has been applied.
 *
 * same as [Math.log]
 */
@JvmName("logTD")
fun <T : Number, D : Dimension> MultiArray<T, D>.log(): NDArray<Double, D> = Numkt.math.mathEx.log(this)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a log function has been applied.
 *
 * same as [Math.log]
 */
@JvmName("logFloatD")
fun <D : Dimension> MultiArray<Float, D>.log(): NDArray<Float, D> = Numkt.math.mathEx.logF(this)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a log function has been applied.
 *
 * same as [Math.log]
 */
@JvmName("logComplexFloatD")
fun <D : Dimension> MultiArray<ComplexFloat, D>.log(): NDArray<ComplexFloat, D> = Numkt.math.mathEx.logCF(this)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a log function has been applied.
 *
 * same as [Math.log]
 */
@JvmName("logComplexDoubleD")
fun <D : Dimension> MultiArray<ComplexDouble, D>.log(): NDArray<ComplexDouble, D> = Numkt.math.mathEx.logCD(this)

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a sin function has been applied.
 *
 * same as [Math.sin]
 */
@JvmName("sinTD")
fun <T : Number, D : Dimension> MultiArray<T, D>.sin(): NDArray<Double, D> = Numkt.math.mathEx.sin(this)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a sin function has been applied.
 *
 * same as [Math.sin]
 */
@JvmName("sinFloatD")
fun <D : Dimension> MultiArray<Float, D>.sin(): NDArray<Float, D> = Numkt.math.mathEx.sinF(this)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a sin function has been applied.
 *
 * same as [Math.sin]
 */
@JvmName("sinComplexFloatD")
fun <D : Dimension> MultiArray<ComplexFloat, D>.sin(): NDArray<ComplexFloat, D> = Numkt.math.mathEx.sinCF(this)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a sin function has been applied.
 *
 * same as [Math.sin]
 */
@JvmName("sinComplexDoubleD")
fun <D : Dimension> MultiArray<ComplexDouble, D>.sin(): NDArray<ComplexDouble, D> = Numkt.math.mathEx.sinCD(this)

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a cos function has been applied.
 *
 * same as [Math.cos]
 */
@JvmName("cosTD")
fun <T : Number, D : Dimension> MultiArray<T, D>.cos(): NDArray<Double, D> = Numkt.math.mathEx.cos(this)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a cos function has been applied.
 *
 * same as [Math.cos]
 */
@JvmName("cosFloatD")
fun <D : Dimension> MultiArray<Float, D>.cos(): NDArray<Float, D> = Numkt.math.mathEx.cosF(this)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a cos function has been applied.
 *
 * same as [Math.cos]
 */
@JvmName("cosComplexFloatD")
fun <D : Dimension> MultiArray<ComplexFloat, D>.cos(): NDArray<ComplexFloat, D> = Numkt.math.mathEx.cosCF(this)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a cos function has been applied.
 *
 * same as [Math.cos]
 */
@JvmName("cosComplexDoubleD")
fun <D : Dimension> MultiArray<ComplexDouble, D>.cos(): NDArray<ComplexDouble, D> = Numkt.math.mathEx.cosCD(this)

/**
 * Returns cumulative sum of all elements in the given ndarray.
 */
fun <T : Number, D : Dimension> MultiArray<T, D>.cumSum(): D1Array<T> = Numkt.math.cumSum(this)
