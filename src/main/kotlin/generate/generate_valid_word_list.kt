package generate

import java.io.File

// THIS SHOULD Be a gradle task....
// one thing at a time
// manual process for now

// is there anyway to call getResource from main()
class GenWordListKt {
  init {
    val validWords = javaClass.classLoader.getResource("valid_words.txt")?.readText()?.lines()
//    println("validWords $validWords")
    writeListToFile(validWords!!)
  }

  private fun writeListToFile(validWords: List<String>) {
    var sl2 = ""
    validWords.forEach {
      sl2 += "\"$it\","
    }
    val wholeFile = """
      val VALID_WORDS = listOf(
        $sl2
      )
      """.trimIndent()
    File("src/main/kotlin/valid_words.kt").writeText(wholeFile)
  }
}

fun main() {
  println("hello generate")
  GenWordListKt()
}