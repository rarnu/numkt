#include <jni.h>

#ifndef NUMKT_JNI_MATH_
#define NUMKT_JNI_MATH_

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    argMax
 * Signature: (Ljava/lang/Object;II[I[II)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_math_JniMath_argMax(JNIEnv *, jobject, jobject, jint, jint, jintArray, jintArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    argMin
 * Signature: (Ljava/lang/Object;II[I[II)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_math_JniMath_argMin(JNIEnv *, jobject, jobject, jint, jint, jintArray, jintArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    exp
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_exp___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    exp
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_exp___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    expC
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_expC___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    expC
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_expC___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    log
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_log___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    log
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_log___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    logC
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_logC___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    logC
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_logC___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sin
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_sin___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sin
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_sin___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sinC
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_sinC___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sinC
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_sinC___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cos
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cos___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cos
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cos___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cosC
 * Signature: ([FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cosC___3FI(JNIEnv *, jobject, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cosC
 * Signature: ([DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cosC___3DI(JNIEnv *, jobject, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([BII[I[I)B
 */
JNIEXPORT jbyte JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3BII_3I_3I(JNIEnv *, jobject, jbyteArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([SII[I[I)S
 */
JNIEXPORT jshort JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3SII_3I_3I(JNIEnv *, jobject, jshortArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([III[I[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3III_3I_3I(JNIEnv *, jobject, jintArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([JII[I[I)J
 */
JNIEXPORT jlong JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3JII_3I_3I(JNIEnv *, jobject, jlongArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([FII[I[I)F
 */
JNIEXPORT jfloat JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3FII_3I_3I(JNIEnv *, jobject, jfloatArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_max
 * Signature: ([DII[I[I)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMax___3DII_3I_3I(JNIEnv *, jobject, jdoubleArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([BII[I[I)B
 */
JNIEXPORT jbyte JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3BII_3I_3I(JNIEnv *, jobject, jbyteArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([SII[I[I)S
 */
JNIEXPORT jshort JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3SII_3I_3I(JNIEnv *, jobject, jshortArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([III[I[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3III_3I_3I(JNIEnv *, jobject, jintArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([JII[I[I)J
 */
JNIEXPORT jlong JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3JII_3I_3I(JNIEnv *, jobject, jlongArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([FII[I[I)F
 */
JNIEXPORT jfloat JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3FII_3I_3I(JNIEnv *, jobject, jfloatArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    array_min
 * Signature: ([DII[I[I)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_math_JniMath_arrayMin___3DII_3I_3I(JNIEnv *, jobject, jdoubleArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([BII[I[I)B
 */
JNIEXPORT jbyte JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3BII_3I_3I(JNIEnv *, jobject, jbyteArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([SII[I[I)S
 */
JNIEXPORT jshort JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3SII_3I_3I(JNIEnv *, jobject, jshortArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([III[I[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3III_3I_3I(JNIEnv *, jobject, jintArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([JII[I[I)J
 */
JNIEXPORT jlong JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3JII_3I_3I(JNIEnv *, jobject, jlongArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([FII[I[I)F
 */
JNIEXPORT jfloat JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3FII_3I_3I(JNIEnv *, jobject, jfloatArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    sum
 * Signature: ([DII[I[I)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_math_JniMath_sum___3DII_3I_3I(JNIEnv *, jobject, jdoubleArray, jint, jint, jintArray, jintArray);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([BII[I[I[BI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3BII_3I_3I_3BI(JNIEnv *, jobject, jbyteArray, jint, jint, jintArray, jintArray, jbyteArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([SII[I[I[SI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3SII_3I_3I_3SI(JNIEnv *, jobject, jshortArray, jint, jint, jintArray, jintArray, jshortArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([III[I[I[II)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3III_3I_3I_3II(JNIEnv *, jobject, jintArray, jint, jint, jintArray, jintArray, jintArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([JII[I[I[JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3JII_3I_3I_3JI(JNIEnv *, jobject, jlongArray, jint, jint, jintArray, jintArray, jlongArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([FII[I[I[FI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3FII_3I_3I_3FI(JNIEnv *, jobject, jfloatArray, jint, jint, jintArray, jintArray, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_math_JniMath
 * Method:    cumSum
 * Signature: ([DII[I[I[DI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_rarnu_numkt_native_math_JniMath_cumSum___3DII_3I_3I_3DI(JNIEnv *, jobject, jdoubleArray, jint, jint, jintArray, jintArray, jdoubleArray, jint);


#ifdef __cplusplus
}
#endif

#endif // NUMKT_JNI_MATH_
