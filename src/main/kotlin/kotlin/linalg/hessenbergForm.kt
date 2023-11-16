package com.rarnu.numkt.kotlin.linalg

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.identity
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.*


/**
 * computes Q, H matrices that
 *
 * a = Q * H * Q.H
 *
 * Q is unitary: Q * Q.H = Id
 *
 * H has all zeros below main subdiagonal:
 *
 *  [#, #, #, #]
 *
 *  [#, #, #, #]
 *
 *  [0, #, #, #]
 *
 *  [0, 0, #, #]
 *
 *  NOTE: inplace function, change matrix [a]
 */
@Suppress("DuplicatedCode")
internal fun upperHessenbergFloat(a: MultiArray<ComplexFloat, D2>): Pair<D2Array<ComplexFloat>, D2Array<ComplexFloat>> {
    val (n, m) = a.shape
    var id = Numkt.identity<ComplexFloat>(n)
    var ans = a as D2Array<ComplexFloat>

    for (i in 1 until n - 1) {
        val (tau, v) = householderTransformComplexFloat(ans[i until n, (i - 1) until m])

        var submatrix = ans[i until n, (i - 1) until m]
        submatrix = applyHouseholderComplexFloat(submatrix, tau, v)
        //copy
        for (i1 in i until n) {
            for (j1 in i - 1 until m) {
                ans[i1, j1] = submatrix[i1 - i, j1 - (i - 1)]
            }
        }

        ans = ans.conjTranspose()

        submatrix = ans[i until n, 0 until m]
        submatrix = applyHouseholderComplexFloat(submatrix, tau, v)
        //copy
        for (i1 in i until n) {
            for (j1 in 0 until m) {
                ans[i1, j1] = submatrix[i1 - i, j1]
            }
        }

        ans = ans.conjTranspose()

        submatrix = applyHouseholderComplexFloat(id[i until id.shape[0], 0 until id.shape[1]], tau, v)
        for (i1 in i until id.shape[0]) {
            for (j1 in 0 until id.shape[1]) {
                id[i1, j1] = submatrix[i1 - i, j1]
            }
        }
    }
    id = id.conjTranspose()

    // cleaning subdiagonal
    for (i in 2 until n) {
        for (j in 0 until i - 1) {
            ans[i, j] = ComplexFloat.zero
        }
    }

    return Pair(id, ans)
}

@Suppress("DuplicatedCode")
internal fun upperHessenbergDouble(a: MultiArray<ComplexDouble, D2>): Pair<D2Array<ComplexDouble>, D2Array<ComplexDouble>> {
    val (n, m) = a.shape
    var id = Numkt.identity<ComplexDouble>(n)
    var ans = a as D2Array<ComplexDouble>

    for (i in 1 until n - 1) {
        val (tau, v) = householderTransformComplexDouble(ans[i until n, (i - 1) until m])

        var submatrix = ans[i until n, (i - 1) until m]
        submatrix = applyHouseholderComplexDouble(submatrix, tau, v)
        //copy
        for (i1 in i until n) {
            for (j1 in i - 1 until m) {
                ans[i1, j1] = submatrix[i1 - i, j1 - (i - 1)]
            }
        }

        ans = ans.conjTranspose()

        submatrix = ans[i until n, 0 until m]
        submatrix = applyHouseholderComplexDouble(submatrix, tau, v)
        //copy
        for (i1 in i until n) {
            for (j1 in 0 until m) {
                ans[i1, j1] = submatrix[i1 - i, j1]
            }
        }

        ans = ans.conjTranspose()

        submatrix = applyHouseholderComplexDouble(id[i until id.shape[0], 0 until id.shape[1]], tau, v)
        for (i1 in i until id.shape[0]) {
            for (j1 in 0 until id.shape[1]) {
                id[i1, j1] = submatrix[i1 - i, j1]
            }
        }
    }
    id = id.conjTranspose()

    // cleaning subdiagonal
    for (i in 2 until n) {
        for (j in 0 until i - 1) {
            ans[i, j] = ComplexDouble.zero
        }
    }

    return Pair(id, ans)
}