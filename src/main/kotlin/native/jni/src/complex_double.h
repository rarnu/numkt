#ifndef NUMKT_COMPLEXDOUBLE_H_
#define NUMKT_COMPLEXDOUBLE_H_

#include "jni.h"

jobject newComplexDouble(JNIEnv *, double, double);

jobject newComplexFloat(JNIEnv *, int64_t);

#endif // NUMKT_COMPLEXDOUBLE_H_
