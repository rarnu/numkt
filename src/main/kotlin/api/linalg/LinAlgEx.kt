package com.rarnu.numkt.api.linalg

import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.*

/**
 * Extension interface for [LinAlg] for improved type support.
 */
interface LinAlgEx {
    /**
     * Returns inverse of a double matrix from numeric matrix
     */
    fun <T : Number> inv(mat: MultiArray<T, D2>): NDArray<Double, D2>

    /**
     * Returns inverse float matrix
     */
    fun invF(mat: MultiArray<Float, D2>): NDArray<Float, D2>

    /**
     * Returns inverse complex matrix
     */
    fun <T : Complex> invC(mat: MultiArray<T, D2>): NDArray<T, D2>

    /**
     * Solve a linear matrix equation, or system of linear scalar equations.
     */
    fun <T : Number, D : Dim2> solve(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<Double, D>

    /**
     * Solve a linear matrix equation, or system of linear scalar equations.
     */
    fun <D : Dim2> solveF(a: MultiArray<Float, D2>, b: MultiArray<Float, D>): NDArray<Float, D>

    /**
     * Solve a linear matrix equation, or system of linear scalar equations.
     */
    fun <T : Complex, D : Dim2> solveC(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<T, D>

    /**
     * Returns norm of float matrix
     */
    fun normF(mat: MultiArray<Float, D2>, norm: Norm = Norm.Fro): Float

    /**
     * Returns norm of double matrix
     */
    fun norm(mat: MultiArray<Double, D2>, norm: Norm = Norm.Fro): Double

    /**
     * Returns QR decomposition of the numeric matrix
     */
    fun <T : Number> qr(mat: MultiArray<T, D2>): Pair<D2Array<Double>, D2Array<Double>>

    /**
     * Returns QR decomposition of the float matrix
     */
    fun qrF(mat: MultiArray<Float, D2>): Pair<D2Array<Float>, D2Array<Float>>

    /**
     * Returns QR decomposition of the complex matrix
     */
    fun <T : Complex> qrC(mat: MultiArray<T, D2>): Pair<D2Array<T>, D2Array<T>>

    /**
     * Returns PLU decomposition of the numeric matrix
     */
    fun <T : Number> plu(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D2Array<Double>, D2Array<Double>>

    /**
     * Returns PLU decomposition of the float matrix
     */
    fun pluF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D2Array<Float>, D2Array<Float>>

    /**
     * Returns PLU decomposition of the complex matrix
     */
    fun <T : Complex> pluC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D2Array<T>, D2Array<T>>

    /**
     * Returns SVD decomposition of the float matrix
     */
    fun svdF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D1Array<Float>, D2Array<Float>>

    /**
     * Returns SVD decomposition of the numeric matrix
     */
    fun <T : Number> svd(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D1Array<Double>, D2Array<Double>>

    /**
     * Returns SVD decomposition of the complex matrix
     */
    fun <T : Complex> svdC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D1Array<T>, D2Array<T>>

    /**
     * Calculates the eigenvalues and eigenvectors of a numeric matrix
     * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
     */
    fun <T : Number> eig(mat: MultiArray<T, D2>): Pair<D1Array<ComplexDouble>, D2Array<ComplexDouble>>

    /**
     * Calculates the eigenvalues and eigenvectors of a float matrix
     * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
     */
    fun eigF(mat: MultiArray<Float, D2>): Pair<D1Array<ComplexFloat>, D2Array<ComplexFloat>>

    /**
     * Calculates the eigenvalues and eigenvectors of a complex matrix
     * @return a pair of a vector of eigenvalues and a matrix of eigenvectors
     */
    fun <T : Complex> eigC(mat: MultiArray<T, D2>): Pair<D1Array<T>, D2Array<T>>

    /**
     * Calculates the eigenvalues of a numeric matrix.
     * @return [ComplexDouble] vector
     */
    fun <T : Number> eigVals(mat: MultiArray<T, D2>): D1Array<ComplexDouble>

    /**
     * Calculates the eigenvalues of a float matrix
     * @return [ComplexFloat] vector
     */
    fun eigValsF(mat: MultiArray<Float, D2>): D1Array<ComplexFloat>

    /**
     * Calculates the eigenvalues of a float matrix
     * @return complex vector
     */
    fun <T : Complex> eigValsC(mat: MultiArray<T, D2>): D1Array<T>

    /**
     * Dot products of two number matrices.
     */
    fun <T : Number> dotMM(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2>

    /**
     * Dot products of two complex matrices.
     */
    fun <T : Complex> dotMMComplex(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2>

    /**
     * Dot products of number matrix and number vector.
     */
    fun <T : Number> dotMV(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1>

    /**
     * Dot products of complex matrix and complex vector.
     */
    fun <T : Complex> dotMVComplex(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1>

    /**
     * Dot products of two number vectors. Scalar product.
     */
    fun <T : Number> dotVV(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T

    /**
     * Dot products of two complex vectors. Scalar product.
     */
    fun <T : Complex> dotVVComplex(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T
}