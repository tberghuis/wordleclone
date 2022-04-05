import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

fun main() = application {
  val state = rememberWindowState(
    placement = WindowPlacement.Maximized,
    position = WindowPosition(1912.dp, 8.dp)
  )
  Window(
    onCloseRequest = ::exitApplication, state,
    onKeyEvent = ::handleKeyPress
  ) {
    MaterialTheme {
      Screen(state)
    }
  }
}

// move to another file???

// how the hell get rid of warning
// This annotation should be used with the compiler argument '-opt-in=kotlin.RequiresOptIn'
@OptIn(ExperimentalComposeUiApi::class)
fun handleKeyPress(event: KeyEvent): Boolean {
//  println("handleKeyPress $event")

  if (event.type != KeyEventType.KeyUp) {
    return false
  }
  when (event.key) {
    Key.Backspace -> {
      vm.removeLetter()
      return true
    }
    Key.Enter -> {
      vm.onKeyUpEnter()
      return true
    }
  }
  val c = event.awtEventOrNull?.keyChar
  if (c?.isLetter() == true) {
    vm.addLetter(c.uppercaseChar())
    return true
  }
  return false
}