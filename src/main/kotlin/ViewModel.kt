import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// I could make this a singleton object, which is bad in regard to testing
class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())

  val snackbarSharedFlow = MutableSharedFlow<String>()

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

    // if not in word list
    // emit show snackbar
    // return
    if (!validWordList.contains(word)) {
      // is this the correct scope for compse desktop?
      CoroutineScope(Dispatchers.Default).launch {
        snackbarSharedFlow.emit("Not in word list")
      }
      return
    }

    wordleStateFlow.value = ws.copy(
      cursorRow = ws.cursorRow + 1
    )
  }
}