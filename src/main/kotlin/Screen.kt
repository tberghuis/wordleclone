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

  val word = wordleState.value.word


  Column {
    Text("hello screen ${wordleState.value.word}")

    Row {
      for (i in word.indices) {
        RenderChar(word[i])
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



