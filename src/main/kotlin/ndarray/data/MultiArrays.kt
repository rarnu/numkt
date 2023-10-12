package com.rarnu.numkt.ndarray.data

fun <T, D : Dimension> MultiArray<T, D>.asDNArray(): NDArray<T, DN> = if (this is NDArray<T, D>) this.asDNArray() else throw ClassCastException("Cannot cast MultiArray to NDArray of dimension n.")
