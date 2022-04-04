// my options
// have a flow of WordleState data class
// or WordleState holds mutablestate objects
// or make WordleState var mutable state


// is it ok to stick all state in single data class?
// probably, doitwrong

data class WordleState(
//  val wordList: List<String> = listOf("00000", "00000", "00000", "00000", "00000", "00000"),
  val wordList: List<String> = listOf("", "", "", "", "", ""),
  val cursorRow: Int = 0,
//  val cursorCol: Int = 0

  val solution: String = "TOMMY",
  // better off to derive this state
//  val typedLettersPositionMatch: Set<Char> = setOf(),
//  val typedLettersMatch: Set<Char> = setOf()
)

