@file:Suppress("UNCHECKED_CAST")

package com.rarnu.numkt.api.io

import com.rarnu.numkt.api.bio.npy.NpyArray
import com.rarnu.numkt.api.bio.npy.NpyFile
import com.rarnu.numkt.api.bio.npy.NpzFile
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.ndarray.data.*
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.notExists

inline fun <reified T : Number, reified D : Dimension> Numkt.readNPY(fileName: String): NDArray<T, D> = readNPY(Path(fileName), DataType.ofKClass(T::class), dimensionClassOf<D>())

inline fun <reified T : Number, reified D : Dimension> Numkt.readNPY(file: File): NDArray<T, D> = readNPY(file.toPath(), DataType.ofKClass(T::class), dimensionClassOf<D>())

inline fun <reified T : Number, reified D : Dimension> Numkt.readNPY(path: Path): NDArray<T, D> {
    if (path.notExists()) throw NoSuchFileException(path.toFile())
    return readNPY(path, DataType.ofKClass(T::class), dimensionClassOf<D>())
}

fun <T : Any, D : Dimension> Numkt.readNPY(path: Path, dtype: DataType, dim: D): NDArray<T, D> {
    if (dtype.isComplex()) throw Exception("NPY format only supports Number types")
    val npyArray: NpyArray = NpyFile.read(path)
    require(npyArray.shape.size == dim.d) { "Not match dimensions: shape of npy array = ${npyArray.shape.joinToString()}, and dimension = ${dim.d}" }

    val data: MemoryView<T> = when (dtype) {
        DataType.DoubleDataType -> MemoryViewDoubleArray(npyArray.asDoubleArray())
        DataType.FloatDataType -> MemoryViewFloatArray(npyArray.asFloatArray())
        DataType.IntDataType -> MemoryViewIntArray(npyArray.asIntArray())
        DataType.LongDataType -> MemoryViewLongArray(npyArray.asLongArray())
        DataType.ShortDataType -> MemoryViewShortArray(npyArray.asShortArray())
        DataType.ByteDataType -> MemoryViewByteArray(npyArray.asByteArray())
        else -> throw Exception("not supported complex arrays")
    } as MemoryView<T>

    return NDArray(data, shape = npyArray.shape, dim = dim)
}

fun Numkt.readNPZ(fileName: String): List<NDArray<out Number, out DimN>> = readNPZ(Path(fileName))

fun Numkt.readNPZ(file: File): List<NDArray<*, out DimN>> = readNPZ(file.toPath())

fun Numkt.readNPZ(path: Path): List<NDArray<out Number, out DimN>> {
    return NpzFile.read(path).use {
        val entries = it.introspect()
        entries.map { entry ->
            val npyArray = it[entry.name]
            val data = when (entry.type.kotlin) {
                DataType.DoubleDataType.clazz -> MemoryViewDoubleArray(npyArray.asDoubleArray())
                DataType.FloatDataType.clazz -> MemoryViewFloatArray(npyArray.asFloatArray())
                DataType.IntDataType.clazz -> MemoryViewIntArray(npyArray.asIntArray())
                DataType.LongDataType.clazz -> MemoryViewLongArray(npyArray.asLongArray())
                DataType.ShortDataType.clazz -> MemoryViewShortArray(npyArray.asShortArray())
                DataType.ByteDataType.clazz -> MemoryViewByteArray(npyArray.asByteArray())
                else -> throw IllegalArgumentException("Unsupported data type: ${entry.type.kotlin}. Only Double, Float, Int, Long, Short and Byte are supported.")
            }
            NDArray(data, shape = npyArray.shape, dim = dimensionOf(npyArray.shape.size))
        }
    }
}

fun <T : Number, D : Dimension> Numkt.writeNPY(fileName: String, ndArray: NDArray<T, D>): Unit = this.writeNPY(Path(fileName), ndArray)

fun <T : Number, D : Dimension> Numkt.writeNPY(file: File, ndArray: NDArray<T, D>): Unit = this.writeNPY(file.toPath(), ndArray)

fun <T : Number, D : Dimension> Numkt.writeNPY(path: Path, ndArray: NDArray<T, D>) {
    when (ndArray.dtype) {
        DataType.DoubleDataType -> NpyFile.write(path, ndArray.data.getDoubleArray(), ndArray.shape)
        DataType.FloatDataType -> NpyFile.write(path, ndArray.data.getFloatArray(), ndArray.shape)
        DataType.IntDataType -> NpyFile.write(path, ndArray.data.getIntArray(), ndArray.shape)
        DataType.LongDataType -> NpyFile.write(path, ndArray.data.getLongArray(), ndArray.shape)
        DataType.ShortDataType -> NpyFile.write(path, ndArray.data.getShortArray(), ndArray.shape)
        DataType.ByteDataType -> NpyFile.write(path, ndArray.data.getByteArray(), ndArray.shape)
        else -> throw IllegalArgumentException("Unsupported data type: ${ndArray.dtype}. Only Double, Float, Int, Long, Short and Byte are supported.")
    }
}

fun Numkt.writeNPZ(path: Path, vararg ndArrays: NDArray<out Number, out Dimension>): Unit = this.writeNPZ(path, ndArrays.asList())

fun Numkt.writeNPZ(path: Path, ndArrays: List<NDArray<out Number, out Dimension>>) {
    NpzFile.write(path).use {
        ndArrays.forEachIndexed { ind, array ->
            when (array.dtype) {
                DataType.DoubleDataType -> it.write("arr_$ind", array.data.getDoubleArray(), array.shape)
                DataType.FloatDataType -> it.write("arr_$ind", array.data.getFloatArray(), array.shape)
                DataType.IntDataType -> it.write("arr_$ind", array.data.getIntArray(), array.shape)
                DataType.LongDataType -> it.write("arr_$ind", array.data.getLongArray(), array.shape)
                DataType.ShortDataType -> it.write("arr_$ind", array.data.getShortArray(), array.shape)
                DataType.ByteDataType -> it.write("arr_$ind", array.data.getByteArray(), array.shape)
                else -> throw IllegalArgumentException("Unsupported data type: ${array.dtype}. Only Double, Float, Int, Long, Short and Byte are supported.")
            }
        }
    }
}
