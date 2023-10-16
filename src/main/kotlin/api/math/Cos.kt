package com.rarnu.numkt.api.math

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns a ndarray of Double from the given ndarray to each element of which a cos function has been applied.
 */
@JvmName("cos")
fun <T : Number, D : Dimension> Math.cos(a: MultiArray<T, D>): NDArray<Double, D> = this.mathEx.cos(a)

/**
 * Returns a ndarray of Float from the given ndarray to each element of which a cos function has been applied.
 */
@JvmName("cosFloat")
fun <D : Dimension> Math.cos(a: MultiArray<Float, D>): NDArray<Float, D> = this.mathEx.cosF(a)

/**
 * Returns a ndarray of [ComplexFloat] from the given ndarray to each element of which a cos function has been applied.
 */
@JvmName("cosComplexFloat")
fun <D : Dimension> Math.cos(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = this.mathEx.cosCF(a)

/**
 * Returns a ndarray of [ComplexDouble] from the given ndarray to each element of which a cos function has been applied.
 */
@JvmName("cosComplexDouble")
fun <D : Dimension> Math.cos(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = this.mathEx.cosCD(a)