package generate

import java.io.File

// THIS SHOULD Be a gradle task....
// one thing at a time
// manual process for now

// is there anyway to call getResource from main()
class GenWordListKt {
  init {
//    val validWords = javaClass.classLoader.getResource("valid_words.txt")?.readText()?.lines()
//    writeListToFile(validWords!!)

    genWords("valid_words.txt", "VALID_WORDS", "src/main/kotlin/valid_words.kt")
    genWords("top_words.txt", "TOP_WORDS", "src/main/kotlin/top_words.kt")
  }

  private fun genWords(resource: String, constant: String, ktFile: String) {
    val words = javaClass.classLoader.getResource(resource)?.readText()?.lines()
    words?.let {
      var sl2 = ""
      words.forEach {
        sl2 += "\"$it\","
      }
      val wholeFile = """
      val $constant = listOf(
        $sl2
      )
      """.trimIndent()
      File(ktFile).writeText(wholeFile)
    }
  }

//  private fun writeListToFile(validWords: List<String>) {
//    var sl2 = ""
//    validWords.forEach {
//      sl2 += "\"$it\","
//    }
//    val wholeFile = """
//      val VALID_WORDS = listOf(
//        $sl2
//      )
//      """.trimIndent()
//    File("src/main/kotlin/valid_words.kt").writeText(wholeFile)
//  }
}

fun main() {
  println("hello generate")
  GenWordListKt()
}