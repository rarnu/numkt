package com.rarnu.numkt.native.linalg

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.identity
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.linalg.LinAlgEx
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

internal object NativeLinAlg : LinAlg {

    override val linAlgEx: LinAlgEx get() = NativeLinAlgEx

    override fun <T : Number> pow(mat: MultiArray<T, D2>, n: Int): NDArray<T, D2> {
        requireSquare(mat.shape)
        if (n == 0) return Numkt.identity(mat.shape[0], mat.dtype)
        return if (n % 2 == 0) {
            val tmp = pow(mat, n / 2)
            NativeLinAlgEx.dotMM(tmp, tmp)
        } else {
            NativeLinAlgEx.dotMM(mat, pow(mat, n - 1))
        }
    }
}