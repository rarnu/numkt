package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D1
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns the matrix product of two numeric matrices.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefMMNumber")
infix fun <T : Number> MultiArray<T, D2>.dot(b: MultiArray<T, D2>): NDArray<T, D2> = Numkt.linalg.linAlgEx.dotMM(this, b)

/**
 * Returns the matrix product of two complex matrices.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefMMComplex")
infix fun <T : Complex> MultiArray<T, D2>.dot(b: MultiArray<T, D2>): NDArray<T, D2> = Numkt.linalg.linAlgEx.dotMMComplex(this, b)

/**
 * Returns the matrix product of a numeric matrix and a numeric vector.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefMVNumber")
infix fun <T : Number> MultiArray<T, D2>.dot(b: MultiArray<T, D1>): NDArray<T, D1> = Numkt.linalg.linAlgEx.dotMV(this, b)

/**
 * Returns the matrix product of a complex matrix and a complex vector.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefMVComplex")
infix fun <T : Complex> MultiArray<T, D2>.dot(b: MultiArray<T, D1>): NDArray<T, D1> = Numkt.linalg.linAlgEx.dotMVComplex(this, b)

/**
 * Returns the product of two numeric vectors.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefVVNumber")
infix fun <T : Number> MultiArray<T, D1>.dot(b: MultiArray<T, D1>): T = Numkt.linalg.linAlgEx.dotVV(this, b)

/**
 * Returns the product of two complex vectors.
 *
 * same as [LinAlg.dot]
 */
@JvmName("dotDefVVComplex")
infix fun <T : Complex> MultiArray<T, D1>.dot(b: MultiArray<T, D1>): T = Numkt.linalg.linAlgEx.dotVVComplex(this, b)