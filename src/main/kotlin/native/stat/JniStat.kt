package com.rarnu.numkt.native.stat

internal object JniStat {
    external fun median(arr: Any, size: Int, dtype: Int): Double
}