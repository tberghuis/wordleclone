import kotlinx.coroutines.flow.MutableStateFlow

class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())


  fun updateWordTest() {
//    val newWord = "H0000"

//    val wordList = wordleStateFlow.value.wordList
    val newWordList = listOf("H0000", "00000", "00000", "00000", "00000", "00000")

    wordleStateFlow.value = wordleStateFlow.value.copy(wordList = newWordList)
  }


}