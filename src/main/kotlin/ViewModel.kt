import kotlinx.coroutines.flow.MutableStateFlow

// I could make this a singleton object, which is bad in regard to testing
class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())

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

    val ws = wordleStateFlow.value
    val word = ws.wordList[ws.cursorRow]

    if (word.isEmpty()) {
      return
    }

    val newWord = word.substring(0, word.length - 1)
    val newWordList = ws.wordList.toMutableList()
    newWordList[ws.cursorRow] = newWord

    wordleStateFlow.value = ws.copy(
      wordList = newWordList
    )
  }

  fun onKeyUpEnter() {
    println("onKeyUpEnter")
    // todo check if game already over return

    val ws = wordleStateFlow.value
    val word = ws.wordList[ws.cursorRow]

    if (word.length != 5) {
      return
    }

    // todo calculate word result


    wordleStateFlow.value = ws.copy(
      cursorRow = ws.cursorRow + 1
    )

  }
}