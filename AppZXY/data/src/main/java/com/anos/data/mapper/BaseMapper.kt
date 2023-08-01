package com.anos.data.mapper

interface BaseMapper<E, O> {
    fun map(entity: E): O
    fun map(entities: List<E>): List<O> {
        return entities.map { map(it) }
    }
}