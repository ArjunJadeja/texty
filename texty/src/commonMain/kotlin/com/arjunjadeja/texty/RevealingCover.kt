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
 * Sealed interface representing different types of covers for revealing text.
 */
sealed interface RevealingCover {

    /** Default cover style for the revealing text. */
    data object Default : RevealingCover

    /**
     * Custom cover style using a specified cover string.
     *
     * @property cover The string used as a cover in the revealing text.
     */
    data class Custom(val cover: String) : RevealingCover
}