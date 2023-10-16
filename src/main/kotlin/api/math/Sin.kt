package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a sin function has been applied.
 */
@JvmName("sin")
fun <T : Number, D : Dimension> Math.sin(a: MultiArray<T, D>): NDArray<Double, D> = this.mathEx.sin(a)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a sin function has been applied.
 */
@JvmName("sinFloat")
fun <D : Dimension> Math.sin(a: MultiArray<Float, D>): NDArray<Float, D> = this.mathEx.sinF(a)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a sin function has been applied.
 */
@JvmName("sinComplexFloat")
fun <D : Dimension> Math.sin(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = this.mathEx.sinCF(a)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a sin function has been applied.
 */
@JvmName("sinComplexDouble")
fun <D : Dimension> Math.sin(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = this.mathEx.sinCD(a)