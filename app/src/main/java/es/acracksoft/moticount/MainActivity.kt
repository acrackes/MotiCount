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

//import es.acracksoft.moticount.ui.component.Timer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.acracksoft.moticount.datos.FraseMotivadora
import es.acracksoft.moticount.ui.component.MyDatePicker
import es.acracksoft.moticount.ui.component.StyleCard
import es.acracksoft.moticount.ui.component.StyleText
import es.acracksoft.moticount.ui.component.Timer
import es.acracksoft.moticount.ui.component.ViewPhrase
import es.acracksoft.moticount.ui.theme.MotiCountTheme
import es.acracksoft.moticount.viewModel.MyViewModel
import kotlinx.coroutines.*


class MainActivity : ComponentActivity() {
    //Implementamos ViewModel
    val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotiCountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content(viewModel)
                }
            }
        }
    }
}

//Contenedor principal de la aplicación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(viewModel: MyViewModel) {
    //Variables
    var dias by remember { mutableStateOf(0) }
    var horas by remember { mutableStateOf(0) }
    var minutos by remember { mutableStateOf(0) }
    var segundos by remember { mutableStateOf(0) }
    var finish by remember { mutableStateOf(false) }
    var isRunnig by remember { mutableStateOf(false)}

    //Calendario
    val calendar = java.util.Calendar.getInstance()
    // set the initial date
    val datePickerState: DatePickerState =
        rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    var showDatePicker: Boolean by remember { mutableStateOf(false) }
    var selectedDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }
    var actualDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }
    var finishDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    //Actualizo variables
    viewModel.frase = ViewPhrase(FraseMotivadora(viewModel.frase))

    Log.d("TAG", "Variable actualDate en el MainActivity: $actualDate")
    //Contenido de la aplicación
    Scaffold(modifier = Modifier.padding(16.dp)) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(state = scrollState)
        ) {
            //Título
            StyleText(
                "TE QUEDAN", 50,
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            //Dias
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyleText("Días:", 60, Modifier.align(Alignment.CenterVertically))
                StyleCard(viewModel.timerState.days, 150, 100, 70)
            }
            //Horas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyleText("Horas:", 50, Modifier.align(Alignment.CenterVertically))
                StyleCard(viewModel.timerState.hours, 150, 100, 70)
            }
            //Minutos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyleText("Minutos:", 40, Modifier.align(Alignment.CenterVertically))
                StyleCard(viewModel.timerState.minutes, 150, 100, 70)
            }
            //Segundos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyleText("Segundos:", 30, Modifier.align(Alignment.CenterVertically))
                StyleCard(viewModel.timerState.seconds, 150, 100, 70)
            }
            //Espacio
            Spacer(modifier = Modifier.height(5.dp))
            //genera una linea sepadora

            //Frases
            StyleText(
                viewModel.frase, 20,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Start)
            )

            //Botón para seleccionar fecha
            MyDatePicker(
                datePickerState,
                showDatePicker,
                selectedDate,
                onShowDatePickerChange = { showDatePicker = it },
                onSelectedDateChange = { selectedDate = it }
            )
            finishDate = selectedDate

            Log.d("TAG", "Fecha final selecionanda en el calendario: $finishDate")
            //Log.d("TAG", "Is running: $isRunning")

            Timer(viewModel = viewModel, finishTimeMillis = finishDate, actualDate = actualDate)


            //Botón para iniciar contador
            Button(
                onClick = {
                    viewModel.isRunning = !viewModel.isRunning
                    Log.d("TAG", "MainActivity-Boton-Is running: ${viewModel.isRunning}")
                }
            ) {
                Text(text = if (viewModel.isRunning) "Detener" else "Iniciar contador")
            }
            //Se terminó el tiempo
            //Log.d("TAG", "Time off")
        }


    }

}