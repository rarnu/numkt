#include "jni_stat.h"
#include "numkt_stat.h"

/*
 * Class:     com_rarnu_numkt_native_stat_JniStat
 * Method:    median
 * Signature: (Ljava/lang/Object;II)D
 */
JNIEXPORT jdouble JNICALL Java_com_rarnu_numkt_native_stat_JniStat_median(JNIEnv *env, jobject jobj, jobject jarr, jint size, jint type) {
    void *varr = env->GetPrimitiveArrayCritical((jarray) jarr, nullptr);
    double ret = array_median(varr, size, type);
    env->ReleasePrimitiveArrayCritical((jarray) jarr, varr, 0);
    return ret;
}