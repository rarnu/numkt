package com.rarnu.numkt.ndarray.data

/**
 * A generic ndarray. Methods in this interface support write access to the ndarray.
 */
interface MutableMultiArray<T, D : Dimension> : MultiArray<T, D> {
    override val data: MemoryView<T>

    override fun copy(): MutableMultiArray<T, D>

    override fun deepCopy(): MutableMultiArray<T, D>

    override fun reshape(dim1: Int): MutableMultiArray<T, D1>

    override fun reshape(dim1: Int, dim2: Int): MutableMultiArray<T, D2>

    override fun reshape(dim1: Int, dim2: Int, dim3: Int): MutableMultiArray<T, D3>

    override fun reshape(dim1: Int, dim2: Int, dim3: Int, dim4: Int): MutableMultiArray<T, D4>

    override fun reshape(dim1: Int, dim2: Int, dim3: Int, dim4: Int, vararg dims: Int): MutableMultiArray<T, DN>

    override fun transpose(vararg axes: Int): MutableMultiArray<T, D>

    override fun squeeze(vararg axes: Int): MutableMultiArray<T, DN>

    override fun unsqueeze(vararg axes: Int): MutableMultiArray<T, DN>
}
