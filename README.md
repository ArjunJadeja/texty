<div align="center">

![Texty Logo](assets/TextyLogo.svg)

[![Kotlin](https://img.shields.io/badge/kotlin-2.0.20-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-compatible-brightgreen.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

**🎨 Create stunning and beautifully styled composable texts across platforms ✨**

</div>

## 📚 Table of Contents

- [💡 About](#-about)
- [🎭 Styles Overview](#-styles-overview)
- [🛠️ Installation](#-installation)
- [🚀 How to Use](#-how-to-use)
- [📦 Samples](#-samples)
- [❤️ Support](#-support)
- [🤝 Contribution](#-contribution)
- [🔗 Connect](#-connect)
- [🙏 Credits](#-credits)
- [📄 License](#-license)

## 💡 About

Texty is built on top of Jetpack Compose Foundation's Basic Text. It is a highly configurable
Compose Multiplatform library that allows you to display text in various styles and has utility
functions like loading and time keeping. Whether you're building an Android, iOS, desktop, or web
application, it provides a unified API to create dynamic and visually appealing text displays.

## 🎭 Styles Overview

Texty offers a variety of styles to create dynamic and engaging text displays. Here's a quick overview of the available styles:

### Normal Styles
[Sliding](#1-sliding) | [Scrolling](#2-scrolling) | [Blinking](#3-blinking) | [Fading](#4-fading) | [Revealing](#5-revealing) | [StickAndReveal](#6-stickandreveal) | [Typing](#7-typing) | [Basic](#8-basic)

### List Styles
[Motion](#1-motion) | [OneByOne](#2-onebyone) | [Sliding List](#3-sliding-list) | [Scrolling List](#4-scrolling-list)

### Utility Styles
[Loading](#1-loading) | [Time Keeping](#2-time-keeping)

## Normal Styles

### 1. Sliding

A fluid display style where text gracefully slides in a chosen direction, creating a smooth, continuous flow that adds motion and elegance to your content.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Sliding())`

**Configuration:**
`DisplayStyle.Sliding(direction: SlidingDirection, duration: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/32305fd9-f75c-4bba-9b45-9ea8694537df" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun Message(message: String) {
    Texty(
        text = message,
        displayStyle = DisplayStyle.Sliding(
            direction = SlidingDirection.TOWARDS_START,
            duration = 12_000L,
            repeat = Repeat.Continuous
        )
    )
}
```

---

### 2. Scrolling

A dynamic style where text flows smoothly in a selected direction - top or bottom. With adjustable duration and repeat options, it captivates and engages viewers effortlessly.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Scrolling())`

**Configuration:**
`DisplayStyle.Scrolling(direction: SlidingDirection, duration: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/e17f3ae0-a542-448c-bb9e-8226fd0939eb" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun EndCredits(credits: String) {
    var showMovieName by remember { mutableStateOf(false) }
    if (showMovieName) Texty(text = "The Quantum Paradox")
    else {
        Texty(
            text = credits,
            displayStyle = DisplayStyle.Scrolling(
                direction = ScrollingDirection.TOWARDS_TOP,
                duration = 30_000L,
                repeat = Repeat.Once,
                onComplete = { showMovieName = true }
            )
        )
    }
}
```

---

### 3. Blinking

A dynamic style that makes text blink with versatile options: blink once, infinitely, for a set time, or a specified count. The blink delay is customizable for added flexibility.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Blinking())`

**Configuration:**
`DisplayStyle.Blinking(interval: Long, repeat: Repeat, onBlink: () -> Unit)`

<video src="https://github.com/user-attachments/assets/44cb65c1-f586-4189-b5fb-1f0f6fa1e975" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun Offer(message: String) {
    var currentColor by remember { mutableStateOf(color1) }
    Texty(
        text = message,
        displayStyle = DisplayStyle.Blinking(
            interval = interval,
            repeat = Repeat.Continuous,
            onBlink = { currentColor = if (currentColor == color1) color2 else color1 }
        )
    )
}
```

---

### 4. Fading

A smooth transition style where text fades in and out. Configurable options include fade duration and fade type, with a callback when the fade completes, allowing for seamless integration into various animations.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Fading(type = FadingType.IN))`

**Configuration:**
`DisplayStyle.Fading(type: FadingType, duration: Long, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/06162da5-c5b4-45a4-a4a5-3a3e523c32b0" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ShowQuote(
    quote: Quote,
    isFadingIn: Boolean,
    onFadeComplete: () -> Unit
) {
    Texty(
        text = quote.text,
        displayStyle = DisplayStyle.Fading(
            type = if (isFadingIn) FadingType.IN else FadingType.OUT,
            duration = 2000L,
            onComplete = onFadeComplete
        )
    )
}
```

---

### 5. Revealing

An engaging style where text is revealed character by character or by total time. Offers configurable options for reveal patterns, delays, and cover text, with a callback when the animation completes. Perfect for adding suspense or a gradual reveal effect.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Revealing())`

**Configuration:**
`DisplayStyle.Revealing(cover: RevealingCover, pattern: RevealingPattern, type: RevealingType, delayBeforeRevealing: Long, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/dcabf180-b3ee-418f-ad74-13dcc6639297" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ShowWord(wordOfTheDay: String) {
    Texty(
        text = wordOfTheDay,
        displayStyle = DisplayStyle.Revealing(
            delayBeforeRevealing = 500L,
            pattern = RevealingPattern.CENTER_TO_SIDES,
            type = RevealingType.ByEachCharacter(delayInMillis = 200L)
        )
    )
}
```

---

### 6. StickAndReveal

A captivating style where a cover sticks to the surface before being revealed, simulating a realistic poster or banner effect. With adjustable delays and directions, it adds drama and depth to your animations.

**Default Implementation:**
`Texty(text = content, displayStyle = DisplayStyle.StickAndReveal())`

**Configuration:**
`DisplayStyle.StickAndReveal(cover: String?, coverStickingDirection: TransitionDirection, coverStickingDelay: Long, delayBeforeReveal: Long, revealingDirection: TransitionDirection, revealingDelay: Long, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/ccb476ff-d647-4b12-a0cd-8841a1c8f81b" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ShowPoster(content: String, cover: String) {
    Texty(
        text = content,
        displayStyle = DisplayStyle.StickAndReveal(
            cover = cover,
            coverStickingDirection = TransitionDirection.TOP_TO_BOTTOM,
            coverStickingDelay = 80L,
            delayBeforeReveal = 1000L,
            revealingDirection = TransitionDirection.BOTTOM_TO_TOP,
            revealingDelay = 70L
        )
    )
}
```

---

### 7. Typing

A typewriter-inspired style that mimics the appearance of text being typed. Ideal for creating a dynamic, real-time effect.

**Default Implementation:**
`Texty(text = text, displayStyle = DisplayStyle.Typing())`

**Configuration:**
`DisplayStyle.Typing(typingDelayPerChar: Long, onTextDisplayed: () -> Unit)`

<video src="https://github.com/user-attachments/assets/13052e62-fb41-4de4-8268-59e95ca0153c" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ChatBubble(message: String) {
    Texty(
        text = message,
        displayStyle = DisplayStyle.Typing(typingDelayPerChar = 50L)
    )
}
```

---

### 8. Basic

The classic one without any effects that focuses on readability with simple, unadorned typography. Ideal for clear and straightforward text presentation.

**Default Implementation:**
`Texty(text = text)`

**Configuration:**
`DisplayStyle.Basic(onTextDisplayed: () -> Unit)`

<video src="https://github.com/user-attachments/assets/a904f2e9-cc36-44a1-8c50-0b9b0d85411b" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun NeonSign(signText: String) {
    Texty(
        text = signText,
        displayStyle = DisplayStyle.Basic()
    )
}
```

---

## List Styles

### 1. Motion

A dynamic style that animates text or frames sequentially, creating a smooth flow. With customizable display delay and flexible repeat options, it delivers continuous motion, perfect for animations or creating a cinematic feel.

**Default Implementation:**
`Texty(textList = frames, displayStyle = ListDisplayStyle.Motion())`

**Configuration:**
`ListDisplayStyle.Motion(delayBeforeNext: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/3140ed8d-e9b4-42e2-96e1-c301cc93c985" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun PikachuAnimation(pikachuFrames: List<String>) {
    Texty(
        textList = pikachuFrames,
        displayStyle = ListDisplayStyle.Motion(
            delayBeforeNext = 80L,
            repeat = Repeat.Continuous
        )
    )
}
```

---

### 2. OneByOne

A fluid, sequential text display where each word or character gracefully appears with customizable effects—Basic, Fading, or Typing.

**Default Implementation:**
`Texty(textList = textList, displayStyle = ListDisplayStyle.OneByOne())`

**Configuration:**
`ListDisplayStyle.OneByOne(transitionStyle: TransitionStyle, displayDuration: Long, transitionInDuration: Long, transitionOutDuration: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/316096fc-35f8-49a6-93bb-18961a4a24fa" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun NewsHeadlines(headlines: List<String>) {
    Texty(
        textList = headlines,
        displayStyle = ListDisplayStyle.OneByOne(
            transitionStyle = TransitionStyle.TYPING,
            displayDuration = 2000L,
            transitionInDuration = 2000L,
            transitionOutDuration = 2000L,
            repeat = Repeat.Continuous
        )
    )
}
```

---

### 3. Sliding List

Same like Sliding but here you can pass list of strings and can add separator for displaying the items in the list.

**Default Implementation:**
`Texty(textList = textList, displayStyle = ListDisplayStyle.SlidingList())`

**Configuration:**
`ListDisplayStyle.SlidingList(separator: String?, direction: SlidingDirection, duration: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/d5059f1a-4b47-4674-9786-b0108886aa88" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun PresentStudents(students: List<String>) {
    Texty(
        textList = students,
        displayStyle = ListDisplayStyle.SlidingList(
            separator = " <-> ",
            direction = SlidingDirection.TOWARDS_START,
            duration = 15000L,
            repeat = Repeat.Continuous
        )
    )
}
```

---

### 4. Scrolling List

Same like normal Scrolling but here you can pass list of strings.

**Default Implementation:**
`Texty(textList = frames, displayStyle = ListDisplayStyle.ScrollingList())`

**Configuration:**
`ListDisplayStyle.ScrollingList(spacing: Dp, direction: ScrollingDirection, duration: Long, repeat: Repeat, onComplete: () -> Unit)`

<video src="https://github.com/user-attachments/assets/5fe8cac3-ba56-4953-b013-b736a44c672b" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ScrollingCredits(credits: List<String>) {
    Texty(
        textList = credits,
        displayStyle = ListDisplayStyle.ScrollingList(
            spacing = 16.dp,
            direction = ScrollingDirection.TOWARDS_TOP,
            duration = 30000L,
            repeat = Repeat.Once
        )
    )
}
```

---

## Utility Styles

### 1. Loading

A versatile style offering various loading animations, including spinner, circular, box, and music bars. Each type features customizable cycle duration, with the music bar option allowing for adjustable bar count.

**Default Implementation:**
`Texty(utility = Utility.Loading())`

**Configuration:**
`Utility.Loading(type: LoadingType)`

<video src="https://github.com/user-attachments/assets/d4fc3234-543c-45e2-b136-4c0f4566dd1a" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ShowLoading() {
    Texty(
        utility = Utility.Loading(
            type = LoadingType.Spinner(cycleDurationInMillis = 500)
        )
    )
    Texty(
        utility = Utility.Loading(
            type = LoadingType.Circular(cycleDurationInMillis = 500)
        )
    )
    Texty(
        utility = Utility.Loading(
            type = LoadingType.MusicBar(barCount = 3)
        )
    )
}
```

---

### 2. Time Keeping

A utility style that displays time in a customizable format, with options for live updates and an adjustable update interval. Perfect for real-time time tracking.

**Default Implementation:**
`Texty(utility = Utility.TimeKeeping())`

**Configuration:**
`Utility.TimeKeeping(format: String, liveUpdate: Boolean, updateInterval: kotlin.time.Duration)`

<video src="https://github.com/user-attachments/assets/66c7e27d-02d4-4646-b98e-6107343d6e4d" width="100%" style="min-width: 400px;" autoplay loop muted playsinline>
Your browser does not support the video tag.
</video>

```kotlin
@Composable
fun ShowTimeKeeping() {
    Texty(
        utility = Utility.TimeKeeping(
            format = "yyyy-MM-dd HH:mm:ss",
            liveUpdate = true
        )
    )
    Texty(
        utility = Utility.TimeKeeping(
            format = "HH:mm:ss",
            liveUpdate = true,
            updateInterval = 1.seconds
        )
    )
}
```

---

## 🛠️ Installation

To use Texty in your project, add the following dependency to your module's `build.gradle.kts` file:

### For Android projects:

```kotlin
dependencies {
    implementation("com.arjunjadeja:texty-android:1.0.0-alpha")
}
```

### For Kotlin Multiplatform projects:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("com.arjunjadeja:texty:1.0.0-alpha")
            }
        }
    }
}
```

Make sure you have `mavenCentral()` in your list of repositories.

### For setting up the project locally:

If you want to set up the Texty project in your local development environment, please refer to
the [Project Setup Guide](SetUpGuide.md).

## 🚀 How to Use

Texty offers three main ways to create styled text:

1. **Normal Text Display**:
   ```kotlin
   import com.arjunjadeja.texty.Texty
   import com.arjunjadeja.texty.DisplayStyle

   @Composable
   fun TextyExample() {
       Texty(
           text = "Hello, Texty!",
           displayStyle = DisplayStyle.Typing()
       )
   }
   ```

2. **List of Texts Display**:
   ```kotlin
   import com.arjunjadeja.texty.Texty
   import com.arjunjadeja.texty.ListDisplayStyle

   @Composable
   fun TextyListExample() {
       Texty(
           textList = listOf("First", "Second", "Third"),
           displayStyle = ListDisplayStyle.OneByOne()
       )
   }
   ```

3. **Utility Display**:
   ```kotlin
   import com.arjunjadeja.texty.Texty
   import com.arjunjadeja.texty.Utility

   @Composable
   fun TextyUtilityExample() {
       Texty(
           utility = Utility.Loading()
       )
   }
   ```

Choose the appropriate `Texty` function based on your needs. Each function offers various display
styles and customization options to create engaging text effects.

## 📦 Samples

- Check out [Releases](https://github.com/ArjunJadeja/texty/releases) to download the latest android
  sample APK (3.1 MB) and dmg file for running on macOS.
- Check the `sample` module for multiple variations and sample implementations of each style.

## ❤️ Support

If you find Texty useful or interesting, consider supporting the project:

- ⭐ **Star** the repository to show your appreciation.
- 👀 **Watch** the repository to get notified on future updates.
- 👤 **Follow me** [ArjunJadeja](https://github.com/ArjunJadeja) for updates and new projects.

Your support helps make Texty better!

## 🤝 Contribution

You can get involved by:

- Trying out the library and sharing feedback
- Suggesting improvements or reporting issues
- Contributing code or documentation
- Assisting with testing and bug fixes

Check [Roadmap](Roadmap.md) and [Contribution Guidelines](Guidelines.md) for more info. All
contributions are welcomed and appreciated.

## 🔗 Connect

Feel free to connect with me for discussing open-source, projects, or anything tech! 😊

[Arjun Jadeja - LinkedIn](https://www.linkedin.com/in/arjun-jadeja/)

## 🙏 Credits

- [Amit Shekhar](https://github.com/amitshekhariitbhu) for guidance and support 😇
- [How to write a Compose Multiplatform Library](https://medium.com/@shubhamsinghshubham777/how-to-write-a-compose-multiplatform-library-66ae1b7edb81)
- [Compose Multiplatform Wizard](https://terrakok.github.io/Compose-Multiplatform-Wizard/) for easy
  setup from [terrakok](https://github.com/terrakok)
- [Publishing KMP Library to Maven central](https://www.youtube.com/watch?v=NPUehp4KpSs)
- [Darshana Jadeja](https://www.linkedin.com/in/darshanajadeja/) for helping out with designs 🤗

## 📄 License

```
Copyright (C) 2024 Arjun Jadeja (arjunjadeja.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```