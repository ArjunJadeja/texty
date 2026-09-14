# Testing guide

Texty UI tests live in **`jvmTest` only** (not `commonTest`). They use **Compose UI Test** (`runComposeUiTest`) on a **headless JVM** desktop runtime—no emulator and no visible window.

### Why `jvmTest`?

`commonTest` is compiled for every KMP target. Compose UI tests need Skiko (`compose.desktop.currentOs`), which is only wired for **JVM**. Putting tests under `src/jvmTest` fixes **Android Studio red errors** on test files and matches what CI runs.

In Android Studio:

1. **Gradle sync** after pulling changes.
2. Open only `src/jvmTest` (there must be **no** `src/commonTest` test folder—duplicate `commonTest` causes red `androidx` errors because that source set has no Skiko classpath).
3. Run tests with the **jvmTest** gutter action (not `commonTest` / Android unit test).
4. If imports stay red: **File → Invalidate Caches → Invalidate and Restart**.

## Stack

| Library | Role |
|---------|------|
| `kotlin("test")` | Test runner and assertions |
| `compose.uiTest` | `setContent`, semantics, `mainClock` |
| `compose.desktop.currentOs` | Skiko for headless Compose on JVM |

Helpers (KDoc on public helpers): `texty/src/jvmTest/.../support/` — not on individual `*StyleTest` classes; use clear `@Test` names instead.

## What we test (philosophy)

Roughly **2–3 focused scenarios per style** (not exhaustive matrices):

- Default / visible behavior
- Completion or callback when the animation should finish
- One meaningful variant (direction, pattern, transition style, etc.)

Sample app: demos compose without crashing + one Voyager `SampleScreen` smoke test.

## Local Gradle commands

| Goal | Command |
|------|---------|
| **CI-equivalent** | `./gradlew testAll --no-daemon` |
| Library | `./gradlew :texty:jvmTest --no-daemon` |
| Sample | `./gradlew :sample:composeApp:jvmTest --no-daemon` |
| One class | `./gradlew :texty:jvmTest --tests "com.arjunjadeja.texty.styles.normal.TypingStyleTest" --no-daemon` |

## Layout

```
texty/src/jvmTest/kotlin/.../styles/   # One *StyleTest.kt per style (2–3 @Test each)
sample/composeApp/src/jvmTest/         # Sample demos + navigation
```

## CI/CD

PRs and pushes to `master` / `dev` run `./gradlew testAll`. Pushes to `master` deploy GitHub Pages after tests pass. See [`.github/workflows/ci.yml`](../.github/workflows/ci.yml).

## Manual release checklist

Automated tests are not visual. Before a release, spot-check all **14** styles on **Android** and **Desktop** (animations, back navigation, theme).
