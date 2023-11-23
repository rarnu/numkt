package com.rarnu.numkt.native

import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.native.linalg.NativeLinAlg
import com.rarnu.numkt.native.math.NativeMath
import com.rarnu.numkt.native.stat.NativeStatistics
import com.rarnu.numkt.api.math.Math

class JvmNativeEngine : NativeEngine() {
    private val loader: Loader by lazy { libLoader("numkt_jni") }

    override fun getMath(): Math {
        if (!loader.loading) loader.load()
        return NativeMath
    }

    override fun getLinAlg(): LinAlg {
        if (!loader.loading) loader.load()
        return NativeLinAlg
    }

    override fun getStatistics(): Statistics {
        if (!loader.loading) loader.load()
        return NativeStatistics
    }
}