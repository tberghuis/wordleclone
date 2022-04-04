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
    val ws = wordleStateFlow.value

    // word being edited
    val word = ws.wordList[ws.cursorRow]
    var newWord = word

    when {
      word.length < 5 -> {
        newWord += letter
      }
      else -> {
        return
      }
    }

    println("addLetter after when $letter")

    val newWordList = ws.wordList.toMutableList()
    newWordList[ws.cursorRow] = newWord

    wordleStateFlow.value = ws.copy(
      wordList = newWordList,
    )
  }

  fun removeLetter() {
    println("removeLetter")
  }

  fun onKeyUpEnter() {
    println("onKeyUpEnter")
  }
}