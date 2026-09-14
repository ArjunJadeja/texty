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

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertTextEquals

/**
 * Advances the Compose [mainClock] and waits until idle.
 *
 * Use inside [androidx.compose.ui.test.runComposeUiTest] for styles that rely on `delay` or
 * animation clocks—not for [kotlinx.coroutines.test.runTest] unless the composition uses that dispatcher.
 *
 * Example: `advanceComposeTime(typingTotalMillis(text, delayPerChar = 1L))` inside `src/jvmTest`.
 */
@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.advanceComposeTime(millis: Long) {
    mainClock.advanceTimeBy(millis)
    waitForIdle()
}

/**
 * Advances virtual time until [predicate] returns true or [timeoutMillis] elapses.
 */
@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.advanceUntil(timeoutMillis: Long = 5_000, stepMillis: Long = 50, predicate: () -> Boolean) {
    var elapsed = 0L
    while (elapsed < timeoutMillis && !predicate()) {
        mainClock.advanceTimeBy(stepMillis)
        waitForIdle()
        elapsed += stepMillis
    }
    if (!predicate()) {
        throw AssertionError("Condition not met within ${timeoutMillis}ms")
    }
}

/**
 * Total milliseconds for a typing animation including the trailing delay in [DisplayStyle.Typing].
 */
fun typingTotalMillis(text: String, delayPerChar: Long): Long {
    val chars = text.length.coerceAtLeast(1)
    return delayPerChar * chars + delayPerChar
}

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.assertTextyTagEquals(expected: String, tag: String = TextyTestTags.ROOT) {
    onNodeWithTag(tag).assertTextEquals(expected)
}
