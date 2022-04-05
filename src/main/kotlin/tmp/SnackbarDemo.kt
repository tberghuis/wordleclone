package tmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SnackbarDemo() {

  // this component to watch a SharedFlow in VM from LaunchedEffect

  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  FloatingActionButton(
    onClick = {
      scope.launch {
        snackbarHostState.showSnackbar("Not in word list")
      }
    },
    content = { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
  )

  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { snackbarData: SnackbarData ->

      Box(Modifier.fillMaxSize()) {
        Card(
          Modifier
            .padding(100.dp)
            .align(Alignment.TopCenter)
            .background(Color.Black)
            .padding(16.dp)
        ) {
          Text(
            modifier = Modifier.background(Color.Black),
            text = snackbarData.message,
            color = Color.White
          )
        }
      }
    }
  )
}