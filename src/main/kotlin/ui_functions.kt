import androidx.compose.ui.graphics.Color


// doitwrong in future use sealed class or enum
// green 0xff6aaa64
// yellow 0xffc9b458
// gray 0xff787c7e

object COLORS {
  val Gray: Color = Color(0xff787c7e)
  val Yellow = Color(0xffc9b458)
  val Green = Color(0xff6aaa64)
}


fun deriveKeyBackgroundColor(key: Char, wordleState: WordleState): Color {

  return COLORS.Gray
}