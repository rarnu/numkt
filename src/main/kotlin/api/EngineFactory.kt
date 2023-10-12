package com.rarnu.numkt.api

interface EngineFactory {
    fun getEngine(type: EngineType? = null): Engine
}