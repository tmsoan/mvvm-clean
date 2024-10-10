package com.anos.network

import kotlinx.serialization.Serializable

/**
 * Wrapper for data provided
 */
@Serializable
data class NetworkResponse<T>(
    val data: T,
)
