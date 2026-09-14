# API documentation

Texty publishes **KDoc-generated HTML** (via [Dokka](https://kotlinlang.org/docs/dokka-introduction.html)) on GitHub Pages alongside the Wasm sample app.

| Resource | URL |
|----------|-----|
| Live sample | https://arjunjadeja.github.io/texty/ |
| API reference | https://arjunjadeja.github.io/texty/api/ |

## Local generation

```bash
# HTML API docs only (output: texty/build/dokka/html/)
./gradlew :texty:dokkaGeneratePublicationHtml --no-daemon

# Full GitHub Pages folder (Wasm sample + api/ subtree)
./gradlew assembleGitHubPages --no-daemon
# Output: build/github-pages/
```

Open `texty/build/dokka/html/index.html` in a browser after the first command.

## Writing KDoc

- Document every **public** type and function under `texty/src/commonMain/kotlin/com/arjunjadeja/texty/`.
- Do not document `com.arjunjadeja.texty.internal` — it is excluded from Dokka output.
- Shared JVM test helpers under `texty/src/jvmTest/.../support/` should have KDoc (see [Guidelines.md](../Guidelines.md)); they are not published to the API site.
- Module-level overview: edit [texty/Module.md](../texty/Module.md) (shown on the Dokka home page).

## CI

On push to `master`, CI runs tests, then `assembleGitHubPages` and deploys `build/github-pages/` to GitHub Pages. See [`.github/workflows/ci.yml`](../.github/workflows/ci.yml).
