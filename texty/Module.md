# Texty

Texty is a [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) library for displaying text with animations, list transitions, and small utilities (loading indicators and clocks).

## Quick start

```kotlin
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty

Texty(
    text = "Hello",
    displayStyle = DisplayStyle.Typing(),
)
```

Use three overloads of [Texty](com.arjunjadeja.texty/index.html):

| Overload | Style parameter | Use when |
|----------|-----------------|----------|
| `Texty(text, displayStyle)` | [DisplayStyle](com.arjunjadeja.texty/-display-style/index.html) | Single string (normal styles) |
| `Texty(textList, displayStyle)` | [ListDisplayStyle](com.arjunjadeja.texty/-list-display-style/index.html) | Rotating or animated lists |
| `Texty(utility)` | [Utility](com.arjunjadeja.texty/-utility/index.html) | Loading or time display |

## Style families

- **Normal** — [DisplayStyle](com.arjunjadeja.texty/-display-style/index.html): Basic, Typing, Blinking, Fading, Sliding, Scrolling, Revealing, StickAndReveal
- **List** — [ListDisplayStyle](com.arjunjadeja.texty/-list-display-style/index.html): Motion, OneByOne, SlidingList, ScrollingList
- **Utility** — [Utility](com.arjunjadeja.texty/-utility/index.html): Loading, TimeKeeping

## More resources

- [README](https://github.com/ArjunJadeja/texty#readme) — tutorials and examples for every style
- [Live sample](https://arjunjadeja.github.io/texty/) — interactive Wasm demo
- [Testing guide](https://github.com/ArjunJadeja/texty/blob/master/docs/TESTING.md)
