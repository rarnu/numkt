package com.rarnu.numkt.kotlin

import com.rarnu.numkt.api.Engine
import com.rarnu.numkt.api.EngineType
import com.rarnu.numkt.api.KEEngineType
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.math.Math
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.kotlin.linalg.KELinAlg
import com.rarnu.numkt.kotlin.math.KEMath
import com.rarnu.numkt.kotlin.stat.KEStatistics


class KEEngine : Engine() {

    override val name: String get() = type.name

    override val type: EngineType get() = KEEngineType

    override fun getMath(): Math = KEMath

    override fun getLinAlg(): LinAlg = KELinAlg

    override fun getStatistics(): Statistics = KEStatistics

}