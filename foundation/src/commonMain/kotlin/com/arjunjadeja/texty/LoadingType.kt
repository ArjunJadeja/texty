/*
 * Copyright (C) 2024 Arjun Jadeja (arjunjadeja.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arjunjadeja.texty

/**
 * Sealed interface representing various loading styles.
 */
sealed interface LoadingType {

    /**
     * Spinner loading type with a customizable cycle duration.
     *
     * @property cycleDurationInMillis Duration of a complete cycle in milliseconds.
     */
    data class Spinner(val cycleDurationInMillis: Int = 600) : LoadingType

    /**
     * Circular loading type with a customizable cycle duration.
     *
     * @property cycleDurationInMillis Duration of a complete cycle in milliseconds.
     */
    data class Circular(val cycleDurationInMillis: Int = 600) : LoadingType

    /**
     * Music bar loading type with customizable bar count and cycle duration.
     *
     * @property barCount Number of bars in the animation.
     * @property cycleDurationInMillis Duration of a complete cycle in milliseconds.
     */
    data class MusicBar(val barCount: Int = 5, val cycleDurationInMillis: Int = 600) : LoadingType
}