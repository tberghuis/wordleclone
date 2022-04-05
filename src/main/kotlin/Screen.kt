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
    RenderKeyboard(wordleState)

    PrintWindowPosition(windowState)

  }
}

// do shit wrong until i read reference source code
// then refactor
@Composable
fun RenderKeyboard(wordleState: WordleState) {
  val renderKeysInRow: @Composable (row: List<String>) -> Unit = { row ->
    for (k in row) RenderKey(
      k,
      deriveKeyBackgroundColor(k[0], wordleState)
    ) {
      vm.addLetter(k[0])
    }
  }

  val row1 = "QWERTYUIOP".toCharArray().map { "$it" }
  val row2 = "ASDFGHJKL".toCharArray().map { "$it" }
  val row3 = "ZXCVBNM".toCharArray().map { "$it" }

  Row {
    renderKeysInRow(row1)
  }
  Row {
    renderKeysInRow(row2)
  }
  Row {
    RenderKey("enter", COLORS.LightGray) {
      println("on click enter")
      vm.onKeyUpEnter()
    }
    renderKeysInRow(row3)
    RenderKey("backspace", COLORS.LightGray) {
      println("on click backspace")
      vm.removeLetter()
    }
  }
}

@Composable
fun RenderKey(k: String, backgroundColor: Color, onClick: () -> Unit) {
  // todo change font color to white if backgroundColor = (gray, green or yellow)
  Box(
    modifier = Modifier
      .padding(10.dp)
      .clickable { onClick() }
      .background(backgroundColor)
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
  if (letter == "") {
    return Color.White
  }
  if (row >= cursorRow) {
    return Color.White
  }
  if (!solution.contains(letter)) {
    return COLORS.Gray
  }
  if (solution[col] == letter[0]) {
    return COLORS.Green
  }
  return COLORS.Yellow
}

@Composable
fun PrintWindowPosition(windowState: WindowState) {
  Button(onClick = {
    println("window position ${windowState.position}")
  }) {
    Text("print window position")
  }
}