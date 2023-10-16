package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a log function has been applied.
 */
@JvmName("log")
fun <T : Number, D : Dimension> Math.log(a: MultiArray<T, D>): NDArray<Double, D> = this.mathEx.log(a)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a log function has been applied.
 */
@JvmName("logFloat")
fun <D : Dimension> Math.log(a: MultiArray<Float, D>): NDArray<Float, D> = this.mathEx.logF(a)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a log function has been applied.
 */
@JvmName("logComplexFloat")
fun <D : Dimension> Math.log(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = this.mathEx.logCF(a)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a log function has been applied.
 */
@JvmName("logComplexDouble")
fun <D : Dimension> Math.log(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = this.mathEx.logCD(a)