import androidx.compose.ui.input.key.KeyEvent
import kotlinx.coroutines.flow.MutableStateFlow

class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())


//  fun updateWordTest() {
//    val newWordList = listOf("H0000", "00000", "00000", "00000", "00000", "00000")
//    wordleStateFlow.value = wordleStateFlow.value.copy(wordList = newWordList)
//  }


  fun addLetter(letter: Char) {
    println("addLetter $letter")

  }


  // so i need to improve my kotlin skills
//  fun addLetter(letter: Char) {
//    println("addLetter $letter")
//    val ws = wordleStateFlow.value
//
//    val word = ws.wordList[ws.cursorRow].toCharArray()
//    word[ws.cursorCol] = letter
//
//    val newWord = String(word)
//
//    var newCursorRow = ws.cursorRow
//    var newCursorCol = ws.cursorCol
//
//    ws.cursorCol.let {
//      when {
//        it < 4 -> {
//          newCursorCol++
//        }
//        else -> {
//          newCursorCol = 0
//          newCursorRow++
//        }
//      }
//    }
//
//    val newWordList = ws.wordList.toMutableList()
//    newWordList[ws.cursorRow] = newWord
//    wordleStateFlow.value = wordleStateFlow.value.copy(
//      wordList = newWordList,
//      cursorCol = newCursorCol
//    )
//  }

  fun removeLetter() {
    println("removeLetter")
  }


}