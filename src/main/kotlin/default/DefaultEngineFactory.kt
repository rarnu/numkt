package com.rarnu.numkt.default

import com.rarnu.numkt.api.*
import com.rarnu.numkt.kotlin.KEEngine
import com.rarnu.numkt.native.JvmNativeEngine

internal object DefaultEngineFactory : EngineFactory {
    override fun getEngine(type: EngineType?): Engine = when (type) {
        null -> error("Pass NativeEngineType of KEEngineType")
        KEEngineType -> KEEngine()
        NativeEngineType -> if (supportNative) JvmNativeEngine() else KEEngine()
        DefaultEngineType -> error("Default Engine doesn't link to Default Engine")
    }

    private val supportNative: Boolean by lazy {
        when ("$os-$arch") {
            "macos-X64", "macos-Arm64", "linux-X64", "linux-Arm64", "mingw-X64" -> true
            else -> false
        }
    }

    private val os: String
        get() {
            val osProperty: String = System.getProperty("os.name").lowercase()
            return when {
                osProperty.contains("mac") -> "macos"
                osProperty.contains("nux") -> "linux"
                osProperty.contains("win") -> "mingw"
                else -> "Unknown os"
            }
        }

    private val arch: String
        get() = when (System.getProperty("os.arch").lowercase()) {
            "amd64", "x86_64", "x86-64", "x64" -> "X64"
            "arm64", "aarch64", "armv8" -> "Arm64"
            else -> "Unknown arch"
        }
}