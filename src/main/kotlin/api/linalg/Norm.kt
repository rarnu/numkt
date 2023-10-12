package com.rarnu.numkt.api.linalg

/**
 * Matrix norm types.
 *
 * @property lapackCode value for lapack
 */
enum class Norm(val lapackCode: Char) {
    /**
     * max(abs(A(i,j)))
     */
    Max('M'),

    /**
     * denotes the  one norm of a matrix (maximum column sum)
     */
    N1('1'),

    /**
     * denotes the  infinity norm  of a matrix  (maximum row sum)
     */
    Inf('I'),

    /**
     * denotes the Frobenius norm of a matrix (square root of sum of squares)
     */
    Fro('F')
}