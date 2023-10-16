package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.Dim2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray
import kotlin.jvm.JvmName

/**
 * Solves a linear matrix equation, or system of linear scalar equations.
 */
@JvmName("solveF")
fun <D : Dim2> LinAlg.solve(a: MultiArray<Float, D2>, b: MultiArray<Float, D>): NDArray<Float, D> = this.linAlgEx.solveF(a, b)

/**
 * Solves a linear matrix equation, or system of linear scalar equations.
 */
@JvmName("solveD")
fun <T : Number, D : Dim2> LinAlg.solve(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<Double, D> = this.linAlgEx.solve(a, b)

/**
 * Solves a linear matrix equation, or system of linear scalar equations.
 */
@JvmName("solveC")
fun <T : Complex, D : Dim2> LinAlg.solve(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<T, D> = this.linAlgEx.solveC(a, b)