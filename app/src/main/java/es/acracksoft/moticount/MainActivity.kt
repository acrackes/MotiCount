package es.acracksoft.moticount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.acracksoft.moticount.ui.theme.MotiCountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotiCountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

//Contenedor principal de la aplicación
@Composable
fun Content() {
    //Contenido de la aplicación
    Scaffold ()
    { padding ->
        Column (modifier = Modifier.padding(padding)) {
            Text(text = "Hola")
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MotiCountTheme {
        Content()
    }
}