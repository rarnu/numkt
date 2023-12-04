#include <jni.h>

#ifndef NUMKT_JNI_LINALG_
#define NUMKT_JNI_LINALG_

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    norm
 * Signature: (CII[FI)F
 */
JNIEXPORT jfloat JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_norm__CII_3FI(JNIEnv *, jobject, jchar, jint, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    norm
 * Signature: (CII[DI)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_norm__CII_3DI(JNIEnv *, jobject, jchar, jint, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    inv
 * Signature: (I[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_inv__I_3FI(JNIEnv *, jobject, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    inv
 * Signature: (I[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_inv__I_3DI(JNIEnv *, jobject, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    invC
 * Signature: (I[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_invC__I_3FI(JNIEnv *, jobject, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    invC
 * Signature: (I[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_invC__I_3DI(JNIEnv *, jobject, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    qr
 * Signature: (II[FI[F)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_qr__II_3FI_3F(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    qr
 * Signature: (II[DI[D)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_qr__II_3DI_3D(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    qrC
 * Signature: (II[FI[F)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_qrC__II_3FI_3F(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    qrC
 * Signature: (II[DI[D)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_qrC__II_3DI_3D(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    plu
 * Signature: (II[FI[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_plu__II_3FI_3I(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jintArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    plu
 * Signature: (II[DI[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_plu__II_3DI_3I(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jintArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    pluC
 * Signature: (II[FI[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_pluC__II_3FI_3I(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jintArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    pluC
 * Signature: (II[DI[I)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_pluC__II_3DI_3I(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jintArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    svd
 * Signature: (II[FI[F[FI[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_svd__II_3FI_3F_3FI_3FI(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray, jfloatArray, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    svd
 * Signature: (II[DI[D[DI[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_svd__II_3DI_3D_3DI_3DI(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jdoubleArray, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    svdC
 * Signature: (II[FI[F[FI[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_svdC__II_3FI_3F_3FI_3FI(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray, jfloatArray, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    svdC
 * Signature: (II[DI[D[DI[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_svdC__II_3DI_3D_3DI_3DI(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jdoubleArray, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    eig
 * Signature: (I[F[FC[F)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_eig__I_3F_3FC_3F(JNIEnv *env, jobject jobj, jint n, jfloatArray j_a, jfloatArray j_w, jchar compute_v, jfloatArray j_v);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    eig
 * Signature: (I[D[DC[D)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_eig__I_3D_3DC_3D(JNIEnv *env, jobject jobj, jint n, jdoubleArray j_a, jdoubleArray j_w, jchar compute_v, jdoubleArray j_v);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    solve
 * Signature: (II[FI[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_solve__II_3FI_3FI(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    solve
 * Signature: (II[DI[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_solve__II_3DI_3DI(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    solveC
 * Signature: (II[FI[FI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_solveC__II_3FI_3FI(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    solveC
 * Signature: (II[DI[DI)I
 */
JNIEXPORT jint JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_solveC__II_3DI_3DI(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMM
 * Signature: (ZI[FIIIZI[FII[F)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMM__ZI_3FIIIZI_3FII_3F(JNIEnv *, jobject, jboolean, jint, jfloatArray, jint, jint, jint, jboolean, jint, jfloatArray, jint, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMM
 * Signature: (ZI[DIIIZI[DII[D)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMM__ZI_3DIIIZI_3DII_3D(JNIEnv *, jobject, jboolean, jint, jdoubleArray, jint, jint, jint, jboolean, jint, jdoubleArray, jint, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMMC
 * Signature: (ZI[FIIIZI[FII[F)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMMC__ZI_3FIIIZI_3FII_3F(JNIEnv *, jobject, jboolean, jint, jfloatArray, jint, jint, jint, jboolean, jint, jfloatArray, jint, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMMC
 * Signature: (ZI[DIIIZI[DII[D)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMMC__ZI_3DIIIZI_3DII_3D(JNIEnv *, jobject, jboolean, jint, jdoubleArray, jint, jint, jint, jboolean, jint, jdoubleArray, jint, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMV
 * Signature: (ZI[FIIII[FI[F)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMV__ZI_3FIIII_3FI_3F(JNIEnv *, jobject, jboolean, jint, jfloatArray, jint, jint, jint, jint, jfloatArray, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMV
 * Signature: (ZI[DIIII[DI[D)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMV__ZI_3DIIII_3DI_3D(JNIEnv *, jobject, jboolean, jint, jdoubleArray, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMVC
 * Signature: (ZI[FIIII[FI[F)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMVC__ZI_3FIIII_3FI_3F(JNIEnv *, jobject, jboolean, jint, jfloatArray, jint, jint, jint, jint, jfloatArray, jint, jfloatArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotMVC
 * Signature: (ZI[DIIII[DI[D)V
 */
JNIEXPORT void JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotMVC__ZI_3DIIII_3DI_3D(JNIEnv *, jobject, jboolean, jint, jdoubleArray, jint, jint, jint, jint, jdoubleArray, jint, jdoubleArray);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotVV
 * Signature: (II[FII[FI)F
 */
JNIEXPORT jfloat JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotVV__II_3FII_3FI(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotVV
 * Signature: (II[DII[DI)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotVV__II_3DII_3DI(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotVVC_0002d0iSFwrc
 * Signature: (II[FII[FI)J
 */
JNIEXPORT jobject JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotVVCF(JNIEnv *, jobject, jint, jint, jfloatArray, jint, jint, jfloatArray, jint);

/*
 * Class:     com_rarnu_numkt_native_linalg_JniLinAlg
 * Method:    dotVVC
 * Signature: (II[DII[DI)Lcom/rarnu/numkt/ndarray/complex/ComplexDouble;
 */
JNIEXPORT jobject JNICALL Java_com_rarnu_numkt_native_linalg_JniLinAlg_dotVVC(JNIEnv *, jobject, jint, jint, jdoubleArray, jint, jint, jdoubleArray, jint);

#ifdef __cplusplus
}
#endif

#endif // NUMKT_JNI_LINALG_
