package com.rarnu.numkt.ndarray.complex

fun Number.toComplexFloat(): ComplexFloat = ComplexFloat(this.toFloat(), 0f)

fun Number.toComplexDouble(): ComplexDouble = ComplexDouble(this.toDouble(), 0.0)

operator fun Byte.plus(other: ComplexFloat): ComplexFloat = other + this
operator fun Short.plus(other: ComplexFloat): ComplexFloat = other + this
operator fun Int.plus(other: ComplexFloat): ComplexFloat = other + this
operator fun Long.plus(other: ComplexFloat): ComplexFloat = other + this
operator fun Float.plus(other: ComplexFloat): ComplexFloat = other + this
operator fun Double.plus(other: ComplexFloat): ComplexDouble = other + this
operator fun Byte.plus(other: ComplexDouble): ComplexDouble = other + this
operator fun Short.plus(other: ComplexDouble): ComplexDouble = other + this
operator fun Int.plus(other: ComplexDouble): ComplexDouble = other + this
operator fun Long.plus(other: ComplexDouble): ComplexDouble = other + this
operator fun Float.plus(other: ComplexDouble): ComplexDouble = other + this
operator fun Double.plus(other: ComplexDouble): ComplexDouble = other + this

operator fun Byte.minus(other: ComplexFloat): ComplexFloat = ComplexFloat(this - other.re, -other.im)
operator fun Short.minus(other: ComplexFloat): ComplexFloat = ComplexFloat(this - other.re, -other.im)
operator fun Int.minus(other: ComplexFloat): ComplexFloat = ComplexFloat(this - other.re, -other.im)
operator fun Long.minus(other: ComplexFloat): ComplexFloat = ComplexFloat(this - other.re, -other.im)
operator fun Float.minus(other: ComplexFloat): ComplexFloat = ComplexFloat(this - other.re, -other.im)
operator fun Double.minus(other: ComplexFloat): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Byte.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Short.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Int.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Long.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Float.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)
operator fun Double.minus(other: ComplexDouble): ComplexDouble = ComplexDouble(this - other.re, -other.im)

operator fun Byte.times(other: ComplexFloat): ComplexFloat = other * this
operator fun Short.times(other: ComplexFloat): ComplexFloat = other * this
operator fun Int.times(other: ComplexFloat): ComplexFloat = other * this
operator fun Long.times(other: ComplexFloat): ComplexFloat = other * this
operator fun Float.times(other: ComplexFloat): ComplexFloat = other * this
operator fun Double.times(other: ComplexFloat): ComplexDouble = other * this
operator fun Byte.times(other: ComplexDouble): ComplexDouble = other * this
operator fun Short.times(other: ComplexDouble): ComplexDouble = other * this
operator fun Int.times(other: ComplexDouble): ComplexDouble = other * this
operator fun Long.times(other: ComplexDouble): ComplexDouble = other * this
operator fun Float.times(other: ComplexDouble): ComplexDouble = other * this
operator fun Double.times(other: ComplexDouble): ComplexDouble = other * this

operator fun Byte.div(other: ComplexFloat): ComplexFloat = ComplexFloat(this.toFloat(), 0f) / other
operator fun Short.div(other: ComplexFloat): ComplexFloat = ComplexFloat(this.toFloat(), 0f) / other
operator fun Int.div(other: ComplexFloat): ComplexFloat = ComplexFloat(this.toFloat(), 0f) / other
operator fun Long.div(other: ComplexFloat): ComplexFloat = ComplexFloat(this.toFloat(), 0f) / other
operator fun Float.div(other: ComplexFloat): ComplexFloat = ComplexFloat(this, 0f) / other
operator fun Double.div(other: ComplexFloat): ComplexDouble = ComplexDouble(this, 0.0) / other
operator fun Byte.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this.toDouble(), 0.0) / other
operator fun Short.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this.toDouble(), 0.0) / other
operator fun Int.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this.toDouble(), 0.0) / other
operator fun Long.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this.toDouble(), 0.0) / other
operator fun Float.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this.toDouble(), 0.0) / other
operator fun Double.div(other: ComplexDouble): ComplexDouble = ComplexDouble(this, 0.0) / other

val Float.i: ComplexFloat get() = ComplexFloat(0f, this)
val Double.i: ComplexDouble get() = ComplexDouble(0.0, this)