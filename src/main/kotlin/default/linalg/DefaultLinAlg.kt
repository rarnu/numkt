package com.rarnu.numkt.default.linalg

import com.rarnu.numkt.api.NativeEngineType
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.linalg.LinAlgEx
import com.rarnu.numkt.default.DefaultEngineFactory
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

object DefaultLinAlg : LinAlg {

    private val natLinAlg = DefaultEngineFactory.getEngine(NativeEngineType).getLinAlg()

    override val linAlgEx: LinAlgEx get() = DefaultLinAlgEx

    override fun <T : Number> pow(mat: MultiArray<T, D2>, n: Int): NDArray<T, D2> = natLinAlg.pow(mat, n)
}