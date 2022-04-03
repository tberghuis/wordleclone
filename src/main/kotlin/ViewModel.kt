import kotlinx.coroutines.flow.MutableStateFlow

class ViewModel {

  val wordleStateFlow = MutableStateFlow(WordleState())


}