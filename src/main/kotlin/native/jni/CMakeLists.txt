cmake_minimum_required(VERSION 3.16)
project(numkt_jni)

set(CMAKE_CXX_STANDARD 14)
set(PLATFORM ${CMAKE_SYSTEM_NAME}-${CMAKE_SYSTEM_PROCESSOR})

if (NOT DEFINED TARGET_OS)
    set(TARGET_OS ${PLATFORM})
endif ()

set(CC ${CMAKE_C_COMPILER})
set(HOSTCC gcc)
set(BINARY 64)
set(CROSS_SUFFIX)
set(USE_OPENMP 0)
set(NUM_THREADS 64)
set(DYNAMIC_LIST)

message("TARGET_OS: ${TARGET_OS}")

if (${TARGET_OS} MATCHES "Linux-x86_64")
    message("current os is linuxX64")
    set(suffix linuxX64)
    set(NO_AVX512 0)
    set(DYNAMIC_ARCH 1)
elseif (${TARGET_OS} MATCHES "Linux-aarch64")
    message("current os is Linux-aarch64")
    set(suffix linuxArm64)
    set(NO_AVX512 0)
    set(DYNAMIC_ARCH 1)
elseif (${TARGET_OS} MATCHES "macosArm64" OR ${TARGET_OS} MATCHES "Darwin-arm64")
    message("current os is macosArm64")
    set(suffix macosArm64)
    set(NO_AVX512 1)
    set(DYNAMIC_LIST "ARMV8 CORTEXA55 CORTEXA72 CORTEXA78")
    include_directories("/opt/homebrew/Cellar/openblas/0.3.24/include")
    link_directories("/opt/homebrew/Cellar/openblas/0.3.24/lib")
elseif (${TARGET_OS} MATCHES "macosX64" OR ${TARGET_OS} MATCHES "Darwin-x86_64")
    message("current os is macosX64")
    set(suffix macosX64)
    set(NO_AVX512 1)
    set(DYNAMIC_ARCH 1)
    include_directories("/usr/local/Cellar/openblas/0.3.24/include")
    link_directories("/usr/local/Cellar/openblas/0.3.24/lib")
elseif (${TARGET_OS} MATCHES "MINGW64")
    message("current os is MINGW64")
    set(suffix mingwX64)
    set(NO_AVX512 1)
    set(DYNAMIC_ARCH 1)
    include_directories("/c/Program Files/Java/jdk-1.8/include")
    include_directories("/c/Program Files/Java/jdk-1.8/include/win32")
    include_directories("/mingw64/include/openblas")
    link_directories("/mingw64/lib")
else ()
    message(SEND_ERROR "Platform ${PLATFORM} is not supported")
    return()
endif ()

set(SRC
        src/complex_double.h
        src/complex_double.cpp
        src/jni_linalg.h
        src/jni_linalg.cpp
        src/jni_math.cpp
        src/jni_math.h
        src/jni_stat.cpp
        src/jni_stat.h
        src/numkt_linalg.cpp
        src/numkt_linalg.h
        src/numkt_math.cpp
        src/numkt_math.h
        src/numkt_stat.cpp
        src/numkt_stat.h
)

if (${TARGET_OS} MATCHES "MINGW64")
    set(LIB_GCC "-lstdc++ -static-libstdc++")
else ()
    set(LIB_GCC "")
endif ()

if (NOT ${TARGET_OS} MATCHES "MINGW64")
    find_package(JNI REQUIRED)
endif ()

set(LIB_NAME numkt_jni-${suffix})

add_library(${LIB_NAME} SHARED ${SRC})
target_compile_options(${LIB_NAME} PRIVATE -std=c++14 -fPIC -O3 -fno-exceptions -ffast-math)
target_include_directories(${LIB_NAME} PUBLIC ${JNI_INCLUDE_DIRS})
target_link_libraries(${LIB_NAME} optimized openblas ${LIB_GCC})
