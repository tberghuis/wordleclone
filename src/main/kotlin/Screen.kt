import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


val vm = ViewModel()


@Composable
fun Screen() {

  val wordleState = vm.wordleStateFlow.collectAsState()

  val wordList = wordleState.value.wordList


  Column {
    Text("hello screen ${wordleState.value.wordList[0]}")

    Row {
      for (i in wordList[0].indices) {
        RenderChar(wordList[0][i])
      }
    }

    Button(onClick = {
      vm.updateWordTest()
    }) {
      Text("update word test")
    }
  }
}


@Composable
fun RenderChar(c: Char) {
  Text("$c")
}



