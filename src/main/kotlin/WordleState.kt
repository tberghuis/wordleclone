// my options
// have a flow of WordleState data class
// or WordleState holds mutablestate objects
// or make WordleState var mutable state


data class WordleState(
  val wordList: List<String> = listOf("00000", "00000", "00000", "00000", "00000", "00000"),
  val cursorRow: Int = 0,
  val cursorCol: Int = 0
)

