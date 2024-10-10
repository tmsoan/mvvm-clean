package com.anos.data.mapper

interface BaseMapper<E, O> {
    fun mapToEntity(data: E): O
    fun mapToEntities(lst: List<E>): List<O> {
        return lst.map { mapToEntity(it) }
    }
}