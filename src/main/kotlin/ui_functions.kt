import androidx.compose.ui.graphics.Color


// doitwrong in future use sealed class or enum
// green 0xff6aaa64
// yellow 0xffc9b458
// gray 0xff787c7e

object COLORS {
  val Gray: Color = Color(0xff787c7e)
  val Yellow = Color(0xffc9b458)
  val Green = Color(0xff6aaa64)
  val LightGray = Color(0xffd3d6da)
}

// this is some bad imperative coding here
fun deriveKeyBackgroundColor(key: Char, wordleState: WordleState): Color {
  if (wordleState.cursorRow == 0) {
    return COLORS.LightGray
  }

  for (row in 0 until wordleState.cursorRow) {
    val word = wordleState.wordList[row]
    for (i in 0..4) {
      if (key == wordleState.solution[i] && key == word[i]) {
        return COLORS.Green
      }
    }
  }

  for (row in 0 until wordleState.cursorRow) {
    val word = wordleState.wordList[row]
    if (word.contains(key) && wordleState.solution.contains(key)) {
      return COLORS.Yellow
    }
  }

  for (row in 0 until wordleState.cursorRow) {
    val word = wordleState.wordList[row]
    if (word.contains(key)) {
      return COLORS.Gray
    }
  }

  return COLORS.LightGray
}

//fun main() {
//  println("hello")
//  val wl = listOf("TOMMY", "", "", "", "", "")
//
//  val ws = WordleState(wordList = wl, cursorRow = 1)
//
//  val c = deriveKeyBackgroundColor('T', ws)
//  println("$c")
//}