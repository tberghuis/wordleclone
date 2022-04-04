import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState

// doing it wrong, global singleton
val vm = ViewModel()

@Composable
fun Screen(windowState: WindowState) {

  val wordleState = vm.wordleStateFlow.collectAsState().value
  val cursorRow = wordleState.cursorRow
  val wordList = wordleState.wordList
  val solution = wordleState.solution

  Column {
    Text("hello screen ${wordleState.wordList[0]}")

    for (i in 0..5) {
      Row {
        for (j in 0..4) {
          if (j >= wordList[i].length) {
            RenderChar(null, i, j, cursorRow, solution)
          } else {
            RenderChar(wordList[i][j], i, j, cursorRow, solution)
          }
        }
      }
    }




    PrintWindowPosition(windowState)

  }
}


@Composable
fun RenderChar(c: Char?, row: Int, col: Int, cursorRow: Int, solution: String) {
  val renderString = if (c == null) "" else "$c".uppercase()

  val backgroundColor = calcBackgroundColor(renderString, row, col, cursorRow, solution)

  Box(
    modifier = Modifier.border(BorderStroke(2.dp, Color.Black))
      .background(backgroundColor)
  ) {
    Text(
      renderString, modifier = Modifier.padding(16.dp)
    )
  }
}

fun calcBackgroundColor(
  letter: String, row: Int, col: Int,
  cursorRow: Int, solution: String
): Color {
  //  println("calcBackgroundColor $letter $row $col $cursorRow $solution")
  // put in consts.kt file
  // green 0xff6aaa64
  // yellow 0xffc9b458
  // gray 0xff787c7e
  if (letter == "") {
    return Color.White
  }
  if (row >= cursorRow) {
    return Color.White
  }
  if (!solution.contains(letter)) {
    // gray
    return Color(0xff787c7e)
  }
  if (solution[col] == letter[0]) {
    // green
    return Color(0xff6aaa64)
  }
  // yellow
  return Color(0xffc9b458)
}

@Composable
fun PrintWindowPosition(windowState: WindowState) {

  Button(onClick = {
    println("window position ${windowState.position}")
  }) {
    Text("print window position")
  }


}