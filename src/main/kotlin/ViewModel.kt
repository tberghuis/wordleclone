import kotlinx.coroutines.flow.MutableStateFlow

class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())


  fun updateWordTest() {
    val newWord = "H0000"
    wordleStateFlow.value = wordleStateFlow.value.copy(word = newWord)
  }


}