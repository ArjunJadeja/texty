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

/**
 * Public API for Texty — animated and utility text for Compose Multiplatform.
 *
 * Entry point: [Texty] composables. Pick a style sealed type ([DisplayStyle], [ListDisplayStyle],
 * or [Utility]) and pass it with your content. All styles share the same text layout parameters
 * as Compose Foundation `BasicText` (modifier, [androidx.compose.ui.text.TextStyle], overflow, lines).
 *
 * Implementation details live in `com.arjunjadeja.texty.internal` and are not part of the stable API.
 */
package com.arjunjadeja.texty
