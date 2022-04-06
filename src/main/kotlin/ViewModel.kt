import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// I could make this a singleton object, which is bad in regard to testing
class ViewModel {
  val wordleStateFlow = MutableStateFlow(WordleState())
  val snackbarSharedFlow = MutableSharedFlow<String>()

  // do shit wrong
  // select random solution startup
  init {
    val solution = TOP_WORDS.random()
    println("init solution $solution")
    wordleStateFlow.value = wordleStateFlow.value.copy(solution = solution)
  }

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

    if (!VALID_WORDS.contains(word)) {
      // is this the correct scope for compose desktop?
      CoroutineScope(Dispatchers.Default).launch {
        snackbarSharedFlow.emit("Not in word list")
      }
      return
    }

    // todo check if game won or lost

    // check if won, add to game state
    if (word == ws.solution) {
      CoroutineScope(Dispatchers.Default).launch {
        snackbarSharedFlow.emit("Winner")
      }
      wordleStateFlow.value = ws.copy(
        gameState = GameState.WON
      )
      return
    }


    wordleStateFlow.value = ws.copy(
      cursorRow = ws.cursorRow + 1
    )
  }
}