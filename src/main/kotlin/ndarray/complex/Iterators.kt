package com.rarnu.numkt.ndarray.complex

abstract class ComplexFloatIterator : Iterator<ComplexFloat> {
    final override fun next(): ComplexFloat = nextComplexFloat()

    abstract fun nextComplexFloat(): ComplexFloat
}

abstract class ComplexDoubleIterator : Iterator<ComplexDouble> {
    final override fun next(): ComplexDouble = nextComplexDouble()

    abstract fun nextComplexDouble(): ComplexDouble
}