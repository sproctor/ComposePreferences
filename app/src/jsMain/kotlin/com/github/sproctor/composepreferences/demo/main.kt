import androidx.compose.ui.window.Window
import com.github.sproctor.composepreferences.demo.DemoContent
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalSettingsApi::class)
fun main() {
    onWasmReady {
        val settings = StorageSettings()
        Window("Compose Preferences") {
            DemoContent(settings.toSuspendSettings())
        }
    }
}
