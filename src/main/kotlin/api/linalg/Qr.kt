package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.D2Array
import com.rarnu.numkt.ndarray.data.MultiArray
import kotlin.jvm.JvmName

/**
 * Returns QR decomposition of the float matrix
 */
@JvmName("qrF")
fun LinAlg.qr(mat: MultiArray<Float, D2>): Pair<D2Array<Float>, D2Array<Float>> = this.linAlgEx.qrF(mat)

/**
 * Returns QR decomposition of the numeric matrix
 */
@JvmName("qrD")
fun <T : Number> LinAlg.qr(mat: MultiArray<T, D2>): Pair<D2Array<Double>, D2Array<Double>> = this.linAlgEx.qr(mat)

/**
 * Returns QR decomposition of the complex matrix
 */
@JvmName("qrC")
fun <T : Complex> LinAlg.qr(mat: MultiArray<T, D2>): Pair<D2Array<T>, D2Array<T>> = this.linAlgEx.qrC(mat)