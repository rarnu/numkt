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

    override val linAlgEx: LinAlgEx
        get() = KELinAlgEx

    override fun <T : Number> pow(mat: MultiArray<T, D2>, n: Int): NDArray<T, D2> {
        if (n == 0) return Numkt.identity(mat.shape[0], mat.dtype)

        return if (n % 2 == 0) {
            val tmp = pow(mat, n / 2)
            dot(tmp, tmp)
        } else {
            dot(mat, pow(mat, n - 1))
        }
    }

//    override fun <T : Number> norm(mat: MultiArray<T, D2>, p: Int): Double {
//        require(p > 0) { "Power $p must be positive" }
//
//        return when (mat.dtype) {
//            DataType.DoubleDataType -> {
//                norm(mat.data.getDoubleArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1], p, mat.consistent)
//            }
//            DataType.FloatDataType -> {
//                norm(mat.data.getFloatArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1], p, mat.consistent)
//            }
//            else -> {
//                norm(mat.data, mat.offset, mat.strides, mat.shape[0], mat.shape[1], p, mat.consistent)
//            }
//        }
//    }
}
