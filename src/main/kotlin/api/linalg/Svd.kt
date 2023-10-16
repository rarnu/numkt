package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D1Array
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.D2Array
import com.rarnu.numkt.ndarray.data.MultiArray
import kotlin.jvm.JvmName

/**
 * Returns SVD decomposition of the float matrix
 */
@JvmName("svdF")
fun LinAlg.svd(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D1Array<Float>, D2Array<Float>> = this.linAlgEx.svdF(mat)

/**
 * Returns SVD decomposition of the numeric matrix
 */
@JvmName("svdD")
fun <T : Number> LinAlg.svd(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D1Array<Double>, D2Array<Double>> = this.linAlgEx.svd(mat)

/**
 * Returns SVD decomposition of the complex matrix
 */
@JvmName("svdC")
fun <T : Complex> LinAlg.svd(mat: MultiArray<T, D2>): Triple<D2Array<T>, D1Array<T>, D2Array<T>> = this.linAlgEx.svdC(mat)