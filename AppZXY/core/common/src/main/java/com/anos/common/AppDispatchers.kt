/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anos.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Qualifier annotation used with Hilt/Dagger for dependency injection of CoroutineDispatcher instances.
 *
 * This annotation helps distinguish between different types of dispatchers when injecting them
 * into classes that need specific threading behavior.
 *
 * Example usage:
 * ```
 * @Inject
 * constructor(
 *     @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
 *     @Dispatcher(AppDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher
 * )
 * ```
 *
 * @param appDispatcher The type of dispatcher to inject
 */
@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val appDispatcher: AppDispatchers)

/**
 * Enum representing different types of coroutine dispatchers used throughout the application.
 *
 * This enum works in conjunction with the @Dispatcher annotation to provide type-safe
 * dependency injection of CoroutineDispatcher instances.
 */
enum class AppDispatchers {
    /**
     * Represents Dispatchers.Default - optimized for CPU-intensive work.
     * Should be used for computationally heavy tasks that don't block the main thread.
     * Examples: data processing, sorting, filtering, mathematical calculations.
     */
    Default,

    /**
     * Represents Dispatchers.IO - optimized for I/O operations.
     * Should be used for network requests, file operations, database queries,
     * and other blocking I/O operations that may take time to complete.
     */
    IO,
}
