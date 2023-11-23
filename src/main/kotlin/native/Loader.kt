package com.rarnu.numkt.native

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

internal interface Loader {
    val loading: Boolean
    fun load(): Boolean
    fun manualLoad(javaPath: String? = null): Boolean
}

internal fun libLoader(name: String): Loader = JvmLoader(name)

internal class JvmLoader(private val name: String) : Loader {

    override val loading: Boolean get() = _loading

    private var _loading: Boolean = false

    private val os: String
        get() {
            val osProperty: String = System.getProperty("os.name").lowercase()
            return when {
                osProperty.contains("mac") -> "macos"
                osProperty.contains("nux") -> "linux"
                osProperty.contains("win") -> "mingw"
                else -> error("Unsupported operating system: $osProperty")
            }
        }

    private val arch: String
        get() = when (val arch: String = System.getProperty("os.arch").lowercase()) {
            "amd64", "x86_64", "x86-64", "x64" -> "X64"
            "arm64", "aarch64", "armv8" -> "Arm64"
            else -> error("Unsupported architecture: $arch")
        }

    private val libraryDir: Path by lazy { Files.createTempDirectory("jni_numkt").apply { toFile().deleteOnExit() } }

    private val nativeNameLib = buildString {
        if (os == "mingw") append("lib")
        append(name)
        append('-')
        append(os)
        append(arch)
    }

    private val prefixPath = when (os) {
        "linux" -> if (arch == "Arm64") "lib/linuxArm64/" else "lib/linuxX64/"
        "macos" -> if (arch == "Arm64") "lib/macosArm64/" else "lib/macosX64/"
        else -> "lib/mingwX64/"
    }

    override fun load(): Boolean {
        val resource = System.mapLibraryName(nativeNameLib)
        val inputStream = Loader::class.java.classLoader.getResourceAsStream("$prefixPath$resource")
        var libraryPath: Path? = null
        return try {
            if (inputStream != null) {
                libraryPath = libraryDir.resolve(resource)
                Files.copy(inputStream, libraryPath!!, StandardCopyOption.REPLACE_EXISTING)
                System.load(libraryPath.toString())
            } else {
                System.loadLibrary(nativeNameLib)
            }
            _loading = true
            true
        } catch (e: Throwable) {
            libraryPath?.toFile()?.delete()
            throw e
        }
    }

    override fun manualLoad(javaPath: String?): Boolean {
        if (javaPath.isNullOrEmpty()) {
            System.loadLibrary(nativeNameLib)
        } else {
            val libFullName = System.mapLibraryName(nativeNameLib)
            val fullPath = if (os == "win") "$javaPath\\$libFullName" else "$javaPath/$libFullName"
            System.load(fullPath)
        }
        _loading = true
        return true
    }
}
