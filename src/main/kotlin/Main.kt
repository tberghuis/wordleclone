// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.awtEvent
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
@Preview
fun App() {
  var text by remember { mutableStateOf("Hello, World!") }

  MaterialTheme {
    Button(onClick = {
      text = "Hello, Desktop!"
    }) {
      Text(text)
    }
  }
}

fun main() = application {

  val state = rememberWindowState(
    placement = WindowPlacement.Maximized,
    position = WindowPosition(1912.dp, 8.dp)
  )


  Window(
    onCloseRequest = ::exitApplication, state,
    onKeyEvent = ::handleKeyPress
  ) {
    Screen(state)
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
    vm.addLetter(c)
    return true
  }
  return false
}