package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Returns inverse float matrix
 */
@JvmName("invF")
fun LinAlg.inv(mat: MultiArray<Float, D2>): NDArray<Float, D2> = this.linAlgEx.invF(mat)

/**
 * Returns inverse of a double matrix from numeric matrix
 */
@JvmName("invD")
fun <T : Number> LinAlg.inv(mat: MultiArray<T, D2>): NDArray<Double, D2> = this.linAlgEx.inv(mat)

/**
 * Returns inverse complex matrix
 */
@JvmName("invC")
fun <T : Complex> LinAlg.inv(mat: MultiArray<T, D2>): NDArray<T, D2> = this.linAlgEx.invC(mat)