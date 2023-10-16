package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D1
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Dot products of two number matrices.
 */
@JvmName("dotMMNumber")
fun <T : Number> LinAlg.dot(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = this.linAlgEx.dotMM(a, b)

/**
 * Dot products of two complex matrices.
 */
@JvmName("dotMMComplex")
fun <T : Complex> LinAlg.dot(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = this.linAlgEx.dotMMComplex(a, b)

/**
 * Dot products of number matrix and number vector.
 */
@JvmName("dotMVNumber")
fun <T : Number> LinAlg.dot(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = this.linAlgEx.dotMV(a, b)

/**
 * Dot products of complex matrix and complex vector.
 */
@JvmName("dotMVComplex")
fun <T : Complex> LinAlg.dot(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = this.linAlgEx.dotMVComplex(a, b)

/**
 * Dot products of two number vectors. Scalar product.
 */
@JvmName("dotVVNumber")
fun <T : Number> LinAlg.dot(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = this.linAlgEx.dotVV(a, b)

/**
 * Dot products of two complex vectors. Scalar product.
 */
@JvmName("dotVVComplex")
fun <T : Complex> LinAlg.dot(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = this.linAlgEx.dotVVComplex(a, b)
