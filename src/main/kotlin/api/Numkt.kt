package com.rarnu.numkt.api

import com.rarnu.numkt.api.Numkt.engine
import com.rarnu.numkt.api.Numkt.engines
import com.rarnu.numkt.api.Numkt.linalg
import com.rarnu.numkt.api.Numkt.math
import com.rarnu.numkt.api.Numkt.stat
import com.rarnu.numkt.api.linalg.LinAlg
import com.rarnu.numkt.api.stat.Statistics
import com.rarnu.numkt.api.math.Math

/**
 * The basic object through which calls all ndarray functions. Gives access to ndarray creation and interfaces [Math],
 * [LinAlg] and [Statistics].
 * Calling [Numkt] will load the engine. The default is "DEFAULT".
 * If no engine is found, then an exception is thrown only when you call an implementation that requires the engine.
 *
 * Note: Through [Numkt], you can set your own interface implementation.
 *
 * @property engine currently used engine.
 * @property engines list of engines.
 * @property math returns the [Math] implementation of the corresponding engine.
 * @property linalg returns the [LinAlg] implementation of the corresponding engine.
 * @property stat returns the [Statistics] implementation of the corresponding engine.
 */
object Numkt {
    val engine: String? get() = Engine.getDefaultEngine()

    private val _engines: MutableMap<String, EngineType> = mutableMapOf(
        "DEFAULT" to DefaultEngineType, "KOTLIN" to KEEngineType, "NATIVE" to NativeEngineType
    )

    val engines: Map<String, EngineType> get() = _engines

    val math: Math get() = Engine.getMath()
    val linalg: LinAlg get() = Engine.getLinAlg()
    val stat: Statistics get() = Engine.getStatistics()

    /**
     * Adds engine to [engines].
     */
    fun addEngine(type: EngineType) {
        if (!_engines.containsKey(type.name)) _engines[type.name] = type
    }

    /**
     * Sets the engine of type [type] as the current implementation.
     */
    fun setEngine(type: EngineType) {
        if (type.name in engines) Engine.setDefaultEngine(type)
    }

    /**
     * Returns a list of [elements]. Sugar for easy array creation.
     */
    operator fun <T> get(vararg elements: T): List<T> = elements.toList()
}
