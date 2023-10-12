package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray


/**
 * Returns norm of float matrix
 */
fun LinAlg.norm(mat: MultiArray<Float, D2>, norm: Norm = Norm.Fro): Float = this.linAlgEx.normF(mat, norm)

/**
 * Returns norm of double matrix
 */
fun LinAlg.norm(mat: MultiArray<Double, D2>, norm: Norm = Norm.Fro): Double = this.linAlgEx.norm(mat, norm)
