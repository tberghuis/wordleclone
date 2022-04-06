package tmp

val tmplines = """
AAHED
ZULUS
ZUNIS
""".trimIndent().lines()

fun main(){
  println("hello lines test")

  println("${tmplines.size}")
  println("${tmplines[0]}")
  println("${tmplines[2]}")
}
