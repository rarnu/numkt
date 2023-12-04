@file:Suppress("UNCHECKED_CAST")

package com.rarnu.numkt.api.math

import java.lang.Math

@JvmName("numberPowerInt")
fun <T : Number> T.pow(n: Int): T = Math.pow(this.toDouble(), n.toDouble()) as T

@JvmName("numberPowerFloat")
fun <T : Number> T.pow(n: Float): Double = Math.pow(this.toDouble(), n.toDouble())

@JvmName("numberPowerDouble")
fun <T : Number> T.pow(n: Double): Double = Math.pow(this.toDouble(), n)