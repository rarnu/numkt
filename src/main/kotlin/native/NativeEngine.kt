package com.rarnu.numkt.native

import com.rarnu.numkt.api.Engine
import com.rarnu.numkt.api.EngineType
import com.rarnu.numkt.api.NativeEngineType
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.native.linalg.NativeLinAlg
import com.rarnu.numkt.native.math.NativeMath
import com.rarnu.numkt.native.stat.NativeStatistics
import com.rarnu.numkt.api.math.Math


open class NativeEngine : Engine() {

    override val name: String get() = type.name

    override val type: EngineType get() = NativeEngineType

    override fun getMath(): Math = NativeMath

    override fun getLinAlg(): LinAlg = NativeLinAlg

    override fun getStatistics(): Statistics = NativeStatistics

}