package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.D2Array
import com.rarnu.numkt.ndarray.data.MultiArray
import kotlin.jvm.JvmName

/**
 * Returns PLU decomposition of the float matrix
 */
@JvmName("pluF")
fun LinAlg.plu(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D2Array<Float>, D2Array<Float>> = this.linAlgEx.pluF(mat)

/**
 * Returns PLU decomposition of the numeric matrix
 */
@JvmName("pluD")
fun <T : Number> LinAlg.plu(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D2Array<Double>, D2Array<Double>> = this.linAlgEx.plu(mat)

/**
 * Returns PLU decomposition of the complex matrix
 */
@JvmName("pluC")
fun <T : Complex> LinAlg.plu(mat: MultiArray<T, D2>): Triple<D2Array<T>, D2Array<T>, D2Array<T>> = this.linAlgEx.pluC(mat)