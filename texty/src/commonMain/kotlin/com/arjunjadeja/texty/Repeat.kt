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
 * Sealed interface representing repeat behaviors for text displaying styles.
 */
sealed interface Repeat {

    /** Occurs once. */
    data object Once : Repeat

    /** Repeats continuously. */
    data object Continuous : Repeat

    /**
     * Repeats for a specified duration.
     *
     * @property duration Duration in milliseconds for how long the animation repeats.
     * @property showAfterComplete Whether to display the final state after completing the duration.
     */
    data class TimeBound(val duration: Long, val showAfterComplete: Boolean) : Repeat

    /**
     * Repeats for a specified number of times.
     *
     * @property count Number of times the animation repeats.
     * @property showAfterComplete Whether to display the final state after the count completes.
     */
    data class CountBound(val count: Int, val showAfterComplete: Boolean) : Repeat
}