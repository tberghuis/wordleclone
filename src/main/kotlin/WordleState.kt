// my options
// have a flow of WordleState data class
// or WordleState holds mutablestate objects
// or make WordleState var mutable state

// is it ok to stick all state in single data class?
// probably, doitwrong

enum class GameState {
  PLAYING, WON, LOST
}

data class WordleState(
  val wordList: List<String> = listOf("", "", "", "", "", ""),
  val cursorRow: Int = 0,
  val solution: String = TOP_WORDS.random(),
  val gameState: GameState = GameState.PLAYING
)