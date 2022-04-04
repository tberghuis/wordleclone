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
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
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
fun handleKeyPress(event: KeyEvent): Boolean {
  println("handleKeyPress $event")

  if (event.type == KeyEventType.KeyUp) {
//    vm.handleKeyPress(event)

  }
  return false
}