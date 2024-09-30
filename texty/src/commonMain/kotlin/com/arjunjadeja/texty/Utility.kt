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

import kotlin.time.Duration.Companion.seconds

/**
 * Defines utility components that can be used in text composables to add functionality such
 * as loading indicators or timekeeping.
 */
sealed interface Utility {

    /**
     * A utility style that displays a loading indicator based on the specified loading type.
     *
     * @property type The type of loading indicator (e.g., spinner, circular, music bar).
     */
    data class Loading(
        val type: LoadingType = LoadingType.Spinner()
    ) : Utility {
        override fun toString(): String = "Loading"
    }

    /**
     * A utility style that displays time, optionally with live updates based on a specified format.
     *
     * @property format The format in which the time is displayed (e.g., "HH:mm:ss").
     * @property liveUpdate Specifies if the time should update live.
     * @property updateInterval The interval at which the time updates when live updates are enabled.
     */
    data class TimeKeeping(
        val format: String = "HH:mm:ss",
        val liveUpdate: Boolean = false,
        val updateInterval: kotlin.time.Duration = 1.seconds
    ) : Utility {
        override fun toString(): String = "Time Keeping"
    }
}