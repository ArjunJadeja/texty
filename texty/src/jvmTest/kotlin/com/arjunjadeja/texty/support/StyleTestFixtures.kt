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

package com.arjunjadeja.texty.support

/**
 * Short literals and timing defaults for deterministic style tests.
 */
object StyleTestFixtures {
    const val SHORT_TEXT = "Hi"
    const val MEDIUM_TEXT = "Hello"
    const val FRAME_A = "A"
    const val FRAME_B = "B"
    const val FRAME_C = "C"

    const val FAST_DELAY_MS = 1L
    const val FAST_ANIMATION_MS = 50L
    const val FAST_BLINK_INTERVAL_MS = 20L

    val MOTION_FRAMES = listOf(FRAME_A, FRAME_B, FRAME_C)
    val LIST_ITEMS = listOf("One", "Two")
}
