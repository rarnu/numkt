package com.rarnu.numkt.api

import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.math.Math
import com.rarnu.numkt.api.stat.Statistics
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Type engine implementations.
 *
 * @param name engine type name
 */
sealed class EngineType(val name: String)

/**
 * Engine type for default implementation.
 */
data object DefaultEngineType : EngineType("DEFAULT")

/**
 * Engine type for "pure kotlin" implementation.
 */
data object KEEngineType : EngineType("KOTLIN")

/**
 * Engine type for implementation with OpenBLAS.
 */
data object NativeEngineType : EngineType("NATIVE")

/**
 * Engine provider.
 */
fun enginesProvider(): Map<EngineType, Engine> {
    val engineList = ServiceLoader.load(Engine::class.java).toList()
    return ConcurrentHashMap(engineList.associateBy { it.type })
}

/**
 * This class gives access to different implementations of [LinAlg], [Math], [Statistics].
 * When initializing [Numkt], it loads engines, by default `DEFAULT` implementation is used.
 *
 * @property name engine name
 * @property type [EngineType]
 */
abstract class Engine {

    protected abstract val name: String

    abstract val type: EngineType

    /**
     * Returns [Math] implementation.
     */
    abstract fun getMath(): Math

    /**
     * Returns [LinAlg] implementation.
     */
    abstract fun getLinAlg(): LinAlg

    /**
     * Returns [Statistics] implementation.
     */
    abstract fun getStatistics(): Statistics

    internal companion object : Engine() {

        private val enginesProvider: Map<EngineType, Engine> = enginesProvider()

        var defaultEngine: EngineType? = null

        fun loadEngine() {
            defaultEngine = when {
                enginesProvider.containsKey(DefaultEngineType) -> DefaultEngineType
                enginesProvider.isNotEmpty() -> enginesProvider.iterator().next().key
                else -> null
            }
        }

        override val name: String
            get() = throw EngineNumktException("For a companion object, the name is undefined.")

        override val type: EngineType
            get() = throw EngineNumktException("For a companion object, the type is undefined.")

        internal fun getDefaultEngine(): String? = defaultEngine?.name ?: loadEngine().let { defaultEngine?.name }

        internal fun setDefaultEngine(type: EngineType) {
            if (!enginesProvider.containsKey(type)) throw EngineNumktException("This type of engine is not available.")
            defaultEngine = type
        }

        override fun getMath(): Math {
            if (enginesProvider.isEmpty()) throw EngineNumktException("The map of engines is empty. Can not provide Math implementation.")
            if (defaultEngine == null) loadEngine()
            return enginesProvider[defaultEngine]?.getMath() ?: throw EngineNumktException("The used engine type is not defined.")
        }

        override fun getLinAlg(): LinAlg {
            if (enginesProvider.isEmpty()) throw EngineNumktException("The map of engines is empty. Can not provide LinAlg implementation.")
            if (defaultEngine == null) loadEngine()
            return enginesProvider[defaultEngine]?.getLinAlg() ?: throw throw EngineNumktException("The used engine type is not defined.")
        }

        override fun getStatistics(): Statistics {
            if (enginesProvider.isEmpty()) throw EngineNumktException("The map of engines is empty. Can not provide Statistics implementation.")
            if (defaultEngine == null) loadEngine()
            return enginesProvider[defaultEngine]?.getStatistics() ?: throw throw EngineNumktException("The used engine type is not defined.")
        }
    }
}

class EngineNumktException(message: String) : Exception(message) {
    constructor() : this("")
}

