import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arjunjadeja.texty.App
import kotlinx.browser.document
import org.w3c.dom.events.Event

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    fun start() {
        val body = document.body ?: return
        ComposeViewport(body) {
            App()
        }
    }

    if (document.body != null) {
        start()
    } else {
        document.addEventListener("DOMContentLoaded", { _: Event -> start() })
    }
}
