package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.D1Array
import com.rarnu.numkt.ndarray.data.D2
import com.rarnu.numkt.ndarray.data.D2Array
import com.rarnu.numkt.ndarray.data.MultiArray
import kotlin.jvm.JvmName

/**
 * Calculates the eigenvalues and eigenvectors of a float matrix
 * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
 */
@JvmName("eigF")
fun LinAlg.eig(mat: MultiArray<Float, D2>): Pair<D1Array<ComplexFloat>, D2Array<ComplexFloat>> = this.linAlgEx.eigF(mat)

/**
 * Calculates the eigenvalues and eigenvectors of a numeric matrix
 * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
 */
@JvmName("eig")
fun <T : Number> LinAlg.eig(mat: MultiArray<T, D2>): Pair<D1Array<ComplexDouble>, D2Array<ComplexDouble>> = this.linAlgEx.eig(mat)

/**
 * Calculates the eigenvalues and eigenvectors of a complex matrix
 * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
 */
@JvmName("eigC")
fun <T : Complex> LinAlg.eig(mat: MultiArray<T, D2>): Pair<D1Array<T>, D2Array<T>> = this.linAlgEx.eigC(mat)

/**
 * Calculates the eigenvalues of a float matrix
 * @return [ComplexFloat] vector
 */
@JvmName("eigValsF")
fun LinAlg.eigVals(mat: MultiArray<Float, D2>): D1Array<ComplexFloat> = this.linAlgEx.eigValsF(mat)

/**
 * Calculates the eigenvalues of a numeric matrix.
 * @return [ComplexDouble] vector
 */
@JvmName("eigVals")
fun <T : Number> LinAlg.eigVals(mat: MultiArray<T, D2>): D1Array<ComplexDouble> = this.linAlgEx.eigVals(mat)

/**
 * Calculates the eigenvalues of a float matrix
 * @return complex vector
 */
@JvmName("eigValsC")
fun <T : Complex> LinAlg.eigVals(mat: MultiArray<T, D2>): D1Array<T> = this.linAlgEx.eigValsC(mat)