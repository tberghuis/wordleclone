import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState

// doing it wrong, global singleton
val vm = ViewModel()

@Composable
fun Screen(windowState: WindowState) {

  val wordleState = vm.wordleStateFlow.collectAsState()

  val wordList = wordleState.value.wordList


  Column {
    Text("hello screen ${wordleState.value.wordList[0]}")

    Row {
      for (i in wordList[0].indices) {
        RenderChar(wordList[0][i])
      }
    }

    Button(onClick = {
      vm.updateWordTest()
    }) {
      Text("update word test")
    }

    PrintWindowPosition(windowState)

  }
}


@Composable
fun RenderChar(c: Char) {

  val renderString = if (c == '0') "" else "$c"

  Box(
    modifier = Modifier.border(BorderStroke(2.dp, Color.Black))
  ) {
    Text(
      renderString, modifier = Modifier.padding(16.dp)

    )
  }
}


@Composable
fun PrintWindowPosition(windowState: WindowState) {

  Button(onClick = {
    println("window position ${windowState.position}")
  }) {
    Text("print window position")
  }


}