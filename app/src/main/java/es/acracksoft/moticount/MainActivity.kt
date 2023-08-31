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
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.acracksoft.moticount.ui.theme.MotiCountTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
    //Variables
    var dias by remember { mutableStateOf(0) }
    var horas by remember { mutableStateOf(0) }
    var minutos by remember { mutableStateOf(0) }
    var segundos by remember { mutableStateOf(0) }
    var frase by remember { mutableStateOf("") }

    //Calendario
    val calendar = java.util.Calendar.getInstance()
    // set the initial date
    val datePickerState: DatePickerState  = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    var showDatePicker: Boolean by remember { mutableStateOf(false) }
    var selectedDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }
    var actualDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }
    var finishDate: Long by remember { mutableLongStateOf(calendar.timeInMillis) }

    frase = "Esto es una frase muy larga para que veamos que tal se " +
            "puede mostrar la frase en todo su explendor" +
            " y que se vea bien"

    //Contenido de la aplicación
    Scaffold(modifier = Modifier.padding(16.dp)) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            //Título
            StyteText(
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
                StyteText("Días:", 60, Modifier.align(Alignment.CenterVertically))
                StyleCard(dias, 150, 100, 70)
            }
            //Horas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyteText("Horas:", 50, Modifier.align(Alignment.CenterVertically))
                StyleCard(horas, 150, 100, 70)
            }
            //Minutos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyteText("Minutos:", 40, Modifier.align(Alignment.CenterVertically))
                StyleCard(minutos, 150, 100, 70)
            }
            //Segundos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StyteText("Segundos:", 30, Modifier.align(Alignment.CenterVertically))
                StyleCard(segundos, 150, 100, 70)
            }
            //Espacio
            Spacer(modifier = Modifier.height(5.dp))
            //genera una linea sepadora

            //Frases
            StyteText(
                frase, 20,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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

            Log.d("TAG", "Selected date: $selectedDate")
            Log.d("TAG", "Actual date: $actualDate")
            Log.d("TAG", "Finish date: $finishDate")

            var timeDifference = calculateTimeDifference(initDate = actualDate, endDate = finishDate)
            Log.d("TAG", "Time difference: $timeDifference")

            //MyDatePicker()
            /*ExtendedFloatingActionButton(
                modifier = Modifier.align(Alignment.End),
                onClick = { MyDatePicker() },
                icon = { Icon(Icons.Filled.Add, "Seleccione fecha") },
                text = { Text(text = "Seleccione fecha") },
            )*/

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(
    datePickerState: DatePickerState,
    showDatePicker: Boolean,
    selectedDate: Long,
    onShowDatePickerChange: (Boolean) -> Unit,
    onSelectedDateChange: (Long) -> Unit
) {
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                false.also { onShowDatePickerChange(it) }
            },
            confirmButton = {
                TextButton(onClick = {
                    false.also { onShowDatePickerChange(it) }
                    datePickerState.selectedDateMillis!!.also { onSelectedDateChange(it) }
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    false.also { onShowDatePickerChange(it) }
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    Button(
        onClick = {
            true.also { onShowDatePickerChange(it) }
        }
    ) {
        Text(text = "Selecciona fecha")
    }

    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
    Text(
        text = "Selected date: ${formatter.format(Date(selectedDate))}"
    )
    Log.d("TAG", "Selected date: $selectedDate")

}

@Composable
fun StyteText(dato: String, sizefont: Int, modifier: Modifier) {
    Text(
        text = dato.toString(),
        modifier = modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        fontSize = sizefont.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun StyleCard(dato: Int, ancho: Int, alto: Int, sizefont: Int) {
    Card(
        modifier = Modifier.size(width = ancho.dp, height = alto.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dato.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = sizefont.sp,
                textAlign = TextAlign.Center,
            )
        }

    }

}

//Función que calcula el tiempo transcurrido entre dos fechas
fun calculateTimeDifference(initDate: Long, endDate: Long): String {
    val timeDifference = endDate - initDate

    val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
    val hours = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60

    //Actualizamos las variables de tiempo
    dias = days
    horas = hours
    minutos = minutes
    segundos = seconds

    return "Days: $days, Hours: $hours, Minutes: $minutes, Seconds: $seconds"
}



@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MotiCountTheme {
        Content()
    }
}
