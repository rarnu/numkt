package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

/**
 * Linear Algebra methods interface.
 */
interface LinAlg {

    /**
     * instance of [LinAlgEx]
     */
    val linAlgEx: LinAlgEx

    /**
     * Raise a square matrix to power [n].
     */
    fun <T : Number> pow(mat: MultiArray<T, D2>, n: Int): NDArray<T, D2>

}