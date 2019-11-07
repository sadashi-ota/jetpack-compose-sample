package jp.sadashi.sample.jetpack.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.CurrentTextStyleProvider
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview

// Simplified version of a typical AppState
class AppState(val counterState: CounterState = CounterState())

@Model
class CounterState(var count: Int = 0)

val customGreen = Color(0xFF1EB980.toInt())
val customSurface = Color(0xFF26282F.toInt())
private val themeColors = MaterialColors(
        primary = customGreen,
        surface = customSurface,
        onSurface = Color.Black
)

@Composable
fun CustomTheme(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        val textStyle = TextStyle(color = Color.Red)
        CurrentTextStyleProvider(value = textStyle) {
            children()
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { MyScreenContent() }
        }
    }
}

@Composable
fun MyApp(child: @Composable() () -> Unit) {
    CustomTheme {
        Surface(color = Color.Yellow) {
            child()
        }
    }
}

@Composable
fun MyScreenContent(appState: AppState = AppState()) {
    Column(modifier = ExpandedHeight, crossAxisAlignment = CrossAxisAlignment.Center) {
        Column(modifier = Flexible(1f), crossAxisAlignment = CrossAxisAlignment.Center) {
            Greeting("Android")
            Divider(color = Color.Black)
            Greeting("there")
        }
        Counter(appState.counterState)
    }
}

@Composable
fun Greeting(name: String) {
    Text(
            text = "Hello $name!",
            modifier = Spacing(24.dp),
            style = +themeTextStyle { h1 }
    )
}

@Composable
fun Counter(state: CounterState) {
    Button(
            text = "I've been clicked ${state.count} times",
            onClick = {
                state.count++
            }
    )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp { MyScreenContent() }
}
