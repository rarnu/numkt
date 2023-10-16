@file:Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")

package com.rarnu.numkt.api.io

import org.apache.commons.csv.CSVFormat
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.*
import com.rarnu.numkt.ndarray.operations.forEach
import java.io.*
import java.nio.charset.Charset
import java.util.zip.GZIPInputStream

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param fileName file path including file name and extensions
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
inline fun <reified T : Any, reified D : Dim2> Numkt.readCSV(fileName: String, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<T, D> = readCSV(fileName, DataType.ofKClass(T::class), dimensionClassOf<D>(), delimiter, charset)

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param fileName file path including file name and extensions
 * @param dtype NDArray element type
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
fun <T, D : Dim2> Numkt.readCSV(fileName: String, dtype: DataType, dim: Dim2, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<T, D> {
    val file = File(fileName)
    if (!file.exists()) throw NoSuchFileException(file)
    return readCSV(file, dtype, dim, delimiter, charset)
}

/**
 * Returns a raw array of dimension 2. The type casts to either [Double] or [ComplexDouble].
 * @param fileName file path including file name and extensions
 * @param dtype NDArray element type
 * @param dim dimension of NDArray
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
fun Numkt.readRaw(fileName: String, dtype: DataType? = null, dim: Dim2? = null, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<*, D2> {
    val file = File(fileName)
    if (!file.exists()) throw NoSuchFileException(file)
    return readRaw(file, dtype, dim, delimiter, charset)
}

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param file csv file
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
inline fun <reified T : Any, reified D : Dim2> Numkt.readCSV(file: File, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<T, D> = readCSV(file, DataType.ofKClass(T::class), dimensionClassOf<D>(), delimiter, charset)

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param dtype NDArray element type
 * @param file csv file
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
fun <T, D : Dim2> Numkt.readCSV(file: File, dtype: DataType, dim: Dim2, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<T, D> = readDelim(FileInputStream(file), dtype, dim, delimiter, charset, isCompressed(file))

/**
 * Returns a raw array of dimension 2. The type casts to either [Double] or [ComplexDouble].
 * @param file csv file
 * @param dtype NDArray element type
 * @param dim dimension of NDArray
 * @param delimiter separator between elements
 * @param charset character encoding, by default is UTF_8
 */
fun Numkt.readRaw(file: File, dtype: DataType? = null, dim: Dim2? = null, delimiter: Char = ',', charset: Charset = Charsets.UTF_8): NDArray<*, D2> = readDelim<Any, D2>(FileInputStream(file), dtype, dim, delimiter, charset, isCompressed(file))

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param inStream data input stream from file
 * @param dtype NDArray element type
 * @param dim dimension of NDArray
 * @param delimiter separator between elements
 * @param isCompressed shows whether the data is compressed, by default is false
 */
fun <T, D : Dim2> Numkt.readDelim(inStream: InputStream, dtype: DataType?, dim: Dim2?, delimiter: Char = ',', charset: Charset, isCompressed: Boolean = false): NDArray<T, D> {
    val reader = if (isCompressed) InputStreamReader(GZIPInputStream(inStream), charset) else BufferedReader(InputStreamReader(inStream, charset))
    return readDelim(reader, CSVFormat.Builder.create(CSVFormat.DEFAULT).setDelimiter(delimiter).build(), dtype, dim)
}

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param reader reading character-input streams
 * @param format csv format from apache
 * @param dtype NDArray element type
 * @param dim dimension of NDArray
 */
fun <T, D : Dim2> Numkt.readDelim(reader: Reader, format: CSVFormat = CSVFormat.DEFAULT, dtype: DataType?, dim: Dim2?): NDArray<T, D> {
    val iSize: Int
    val jSize: Int
    val data: MemoryView<T>

    format.parse(reader).use { csvParser ->
        val records = csvParser.records
        iSize = records.size
        jSize = records.first().size()
        val type = dtype ?: records[0][0].parseDtype()
        data = initMemoryView(iSize * jSize, type)

        var index = 0
        for (record in records) {
            for (el in record) {
                data[index++] = el.toType(type)
            }
        }
    }

    val d = dim ?: D2
    return (if (d == D1) D1Array(data, 0, intArrayOf(jSize * iSize), dim = D1) else D2Array(data, 0, intArrayOf(iSize, jSize), dim = D2)) as NDArray<T, D>
}

private val regexComplexDouble = Regex("-?[0-9]+\\.?[0-9e\\-\\d]*")

private fun String.parseDtype(): DataType = when (regexComplexDouble.findAll(this).toList().size) {
    1 -> DataType.DoubleDataType
    2 -> DataType.ComplexDoubleDataType
    else -> throw TypeCastException("Unknown type $this element")
}

private fun <T> String.toType(dtype: DataType): T = when (dtype) {
    DataType.DoubleDataType -> this.toDouble()
    DataType.FloatDataType -> this.toFloat()
    DataType.IntDataType -> this.toInt()
    DataType.LongDataType -> this.toLong()
    DataType.ComplexDoubleDataType -> this.toComplexDouble()
    DataType.ComplexFloatDataType -> this.toComplexFloat()
    DataType.ShortDataType -> this.toShort()
    DataType.ByteDataType -> this.toByte()
} as T

private fun String.toComplexDouble(): ComplexDouble {
    val (re, im) = regexComplexDouble.findAll(this).toList()
    return ComplexDouble(re.value.toDouble(), im.value.toDouble())
}

private fun String.toComplexFloat(): ComplexFloat {
    val (re, im) = regexComplexDouble.findAll(this).toList()
    return ComplexFloat(re.value.toFloat(), im.value.toFloat())
}

private fun isCompressed(file: File) = listOf("gz", "zip").contains(file.extension)

/**
 * Writes an NDArray to csv file. The NDArray must be up to the second dimension.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param file file where the data will be written, if the file does not exist, it will be created
 * @param delimiter separator between elements
 */
fun <T, D : Dim2> Numkt.writeCSV(file: File, ndarray: NDArray<T, D>, delimiter: Char = ','): Unit = writeCSV(FileWriter(file), ndarray, CSVFormat.Builder.create(CSVFormat.DEFAULT).setDelimiter(delimiter).build())

/**
 * Writes an NDArray to csv file. The NDArray must be up to the second dimension.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param path file path where the data will be written
 * @param delimiter separator between elements
 */
fun <T, D : Dim2> Numkt.writeCSV(path: String, ndarray: NDArray<T, D>, delimiter: Char = ','): Unit = writeCSV(FileWriter(path), ndarray, CSVFormat.Builder.create(CSVFormat.DEFAULT).setDelimiter(delimiter).build())

/**
 * Returns an NDArray of type [T] and [D] dimension read from csv file.
 * @param T NDArray element type
 * @param D dimension of NDArray. It can be 1 or 2
 * @param writer
 * @param ndarray array of data
 * @param format csv format from apache
 */
fun <T, D : Dim2> Numkt.writeCSV(writer: Appendable, ndarray: NDArray<T, D>, format: CSVFormat = CSVFormat.DEFAULT): Unit = format.print(writer).use { printer ->
    if (ndarray.dim.d == 1) {
        ndarray.forEach { printer.printRecord(it) }
    } else {
        ndarray as D2Array<T>
        for (i in 0 until ndarray.shape[0]) {
            for (j in 0 until ndarray.shape[1]) {
                printer.print(ndarray[i, j])
            }
            printer.println()
        }
    }
}
