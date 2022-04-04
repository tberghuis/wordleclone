import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
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

    Spacer(Modifier.height(100.dp))
    RenderKeyboard()

    PrintWindowPosition(windowState)

  }
}

// do shit wrong until i read reference source code
// then refactor
@Composable
fun RenderKeyboard() {
//  Text("keyboard here")
  val row1 = "QWERTYUIOP".toCharArray().map { "$it" }
  val row2 = "ASDFGHJKL".toCharArray().map { "$it" }
  val row3 = "ZXCVBNM".toCharArray().map { "$it" }

  val onClick = { println("on click") }

  Row() {
    for (k in row1) RenderKey(k, onClick)
  }
  Row {
    for (k in row2) RenderKey(k, onClick)
  }
  Row {
    RenderKey("enter", onClick)
    for (k in row3) RenderKey(k, onClick)
    RenderKey("backspace", onClick)
  }

}

@Composable
fun RenderKey(k: String, onClick: () -> Unit) {
  Box(
    // todo use wordle color from consts.kt
    modifier = Modifier
      .padding(10.dp)
      .clickable { onClick() }
      .background(Color.Cyan)

  ) {
    Text(
      k, modifier = Modifier.padding(16.dp)
    )
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