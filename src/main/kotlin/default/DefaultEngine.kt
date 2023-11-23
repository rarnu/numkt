package com.rarnu.numkt.default

import com.rarnu.numkt.api.DefaultEngineType
import com.rarnu.numkt.api.Engine
import com.rarnu.numkt.api.EngineType
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.math.Math
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.default.linalg.DefaultLinAlg
import com.rarnu.numkt.default.math.DefaultMath
import com.rarnu.numkt.default.stat.DefaultStatistics

class DefaultEngine : Engine() {
    override val name: String get() = type.name

    override val type: EngineType get() = DefaultEngineType

    override fun getMath(): Math = DefaultMath

    override fun getLinAlg(): LinAlg = DefaultLinAlg

    override fun getStatistics(): Statistics = DefaultStatistics

}