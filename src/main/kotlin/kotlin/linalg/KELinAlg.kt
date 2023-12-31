package com.rarnu.numkt.kotlin.linalg

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.identity
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.linalg.LinAlgEx
import com.rarnu.numkt.api.linalg.dot
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

internal object KELinAlg : LinAlg {

    override val linAlgEx: LinAlgEx get() = KELinAlgEx

    override fun <T : Number> pow(mat: MultiArray<T, D2>, n: Int): NDArray<T, D2> {
        if (n == 0) return Numkt.identity(mat.shape[0], mat.dtype)

        return if (n % 2 == 0) {
            val tmp = pow(mat, n / 2)
            dot(tmp, tmp)
        } else {
            dot(mat, pow(mat, n - 1))
        }
    }
}
