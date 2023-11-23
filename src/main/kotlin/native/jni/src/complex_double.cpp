#include "jni.h"

static jmethodID newComplexDoubleID = nullptr;

jobject newComplexDouble(JNIEnv *env, double re, double im) {
    jobject ret;
    jclass cls = env->FindClass("com/rarnu/numkt/ndarray/complex/ComplexDouble");
    jfieldID companionField = env->GetStaticFieldID(cls, "Companion", "Lcom/rarnu/numkt/ndarray/complex/ComplexDouble$Companion;");
    jobject companionObject = env->GetStaticObjectField(cls, companionField);
    jclass companionClass = env->GetObjectClass(companionObject);
    newComplexDoubleID = env->GetMethodID(companionClass, "invoke", "(DD)Lcom/rarnu/numkt/ndarray/complex/ComplexDouble;");
    ret = env->CallObjectMethod(companionObject, newComplexDoubleID, re, im);
    return ret;
}

jobject newComplexFloat(JNIEnv *env, int64_t num) {
    jobject ret;
    jclass cls = env->FindClass("com/rarnu/numkt/ndarray/complex/ComplexFloat");
    jmethodID newComplexFloatID = env->GetMethodID(cls, "<init>", "(J)V");
    ret = env->NewObject(cls, newComplexFloatID, num);
    return ret;
}