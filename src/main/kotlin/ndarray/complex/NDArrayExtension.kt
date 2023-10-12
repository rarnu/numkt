package com.rarnu.numkt.ndarray.complex

import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import com.rarnu.numkt.ndarray.operations.map
import kotlin.jvm.JvmName

/**
 * Transforms this [NDArray] of [ComplexFloat] to an [NDArray] of the real part of complex numbers.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of real portion of [ComplexFloat]
 */
@get:JvmName("reFloat")
val <D : Dimension> MultiArray<ComplexFloat, D>.re: NDArray<Float, D> get() = this.map { it.re }

/**
 * Transforms this [NDArray] of [ComplexDouble] to an [NDArray] of the real part of complex numbers.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of real portion of [ComplexDouble]
 */
@get:JvmName("reDouble")
val <D : Dimension> MultiArray<ComplexDouble, D>.re: NDArray<Double, D> get() = this.map { it.re }

/**
 * Transforms this [NDArray] of [ComplexFloat] to an [NDArray] of the imaginary part of complex numbers.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of imaginary portion of [ComplexFloat]
 */
@get:JvmName("imFloat")
val <D : Dimension> MultiArray<ComplexFloat, D>.im: NDArray<Float, D> get() = this.map { it.im }

/**
 * Transforms this [NDArray] of [ComplexDouble] to an [NDArray] of the imaginary part of complex numbers.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of imaginary portion of [ComplexDouble]
 */
@get:JvmName("imDouble")
val <D : Dimension> MultiArray<ComplexDouble, D>.im: NDArray<Double, D> get() = this.map { it.im }

/**
 * Transforms this [MultiArray] of [ComplexDouble] to an [NDArray] of the conjugated value.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of conjugated [ComplexDouble]
 */
@JvmName("conjDouble")
fun <D : Dimension> MultiArray<ComplexDouble, D>.conj(): MultiArray<ComplexDouble, D> = this.map { it.conjugate() }

/**
 * Transforms this [MultiArray] of [ComplexFloat] to an [NDArray] of the conjugated value.
 * Dimensions are preserved.
 *
 * @param D dimension.
 * @return [NDArray] of conjugated [ComplexFloat]
 */
@JvmName("conjFloat")
fun <D : Dimension> MultiArray<ComplexFloat, D>.conj(): MultiArray<ComplexFloat, D> = this.map { it.conjugate() }
