package generate

import java.io.File

// THIS SHOULD Be a gradle task....
// one thing at a time
// manual process for now

// why i need class
class GenWordListKt {
  init {
    val validWords = javaClass.classLoader.getResource("valid_words.txt")?.readText()?.lines()

    println("validWords $validWords")

  }

}


fun main() {
  println("hello generate")
//  GenWordListKt()

  val sl = listOf("string", "list", "hello")

  var sl2 = ""
  sl.forEach {
    sl2 += "\"$it\","
  }

  val wholeFile = """
  val VALID_WORDS = listOf(
  $sl2
  )
""".trimIndent()
  File("src/main/kotlin/valid_words.kt").writeText(wholeFile)
}