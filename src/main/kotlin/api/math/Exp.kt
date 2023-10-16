package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns a ndarray of Double from the given ndarray to each element of which an exp function has been applied.
 */
@JvmName("exp")
fun <T : Number, D : Dimension> Math.exp(a: MultiArray<T, D>): NDArray<Double, D> = this.mathEx.exp(a)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which an exp function has been applied.
 */
@JvmName("expFloat")
fun <D : Dimension> Math.exp(a: MultiArray<Float, D>): NDArray<Float, D> = this.mathEx.expF(a)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which an exp function has been applied.
 */
@JvmName("expComplexFloat")
fun <D : Dimension> Math.exp(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = this.mathEx.expCF(a)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which an exp function has been applied.
 */
@JvmName("expComplexDouble")
fun <D : Dimension> Math.exp(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = this.mathEx.expCD(a)