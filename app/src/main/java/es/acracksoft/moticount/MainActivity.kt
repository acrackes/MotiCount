/*
 * Copyright (c) 2023.
 * Create by: Acrack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.acracksoft.moticount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.acracksoft.moticount.ui.theme.MotiCountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotiCountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
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
    Scaffold() { padding ->
        Column(modifier = Modifier.padding(padding)) {
            StyleCard(1, 200, 200)
        }

    }

}

@Composable
fun StyleCard(dato: Int, ancho: Int, alto: Int) {
    Card(
        modifier = Modifier.size(width = ancho.dp, height = alto.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = dato.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 40.dp,
            )
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