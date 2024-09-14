import androidx.compose.ui.window.ComposeUIViewController
import com.arjunjadeja.texty.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
