#include <jni.h>

#ifndef NUMKT_JNI_STAT_
#define NUMKT_JNI_STAT_

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_rarnu_numkt_native_stat_JniStat
 * Method:    median
 * Signature: (Ljava/lang/Object;II)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_stat_JniStat_median(JNIEnv *, jobject, jobject, jint, jint);

#ifdef __cplusplus
}
#endif

#endif // NUMKT_JNI_STAT_
