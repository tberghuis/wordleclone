import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


val vm = ViewModel()


@Composable
fun Screen() {

  val wordleState = vm.wordleStateFlow.collectAsState()

  Text("hello screen ${wordleState.value.word}")
}
