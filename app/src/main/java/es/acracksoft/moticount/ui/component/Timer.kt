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

package es.acracksoft.moticount.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import es.acracksoft.moticount.viewModel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit


@Composable
fun Timer(
    viewModel: MyViewModel,
    finishTimeMillis: Long,
    actualDate: Long
) {
    //var isRunning by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var contador = 0

    Log.d("TAG", "Timer-Contador: ${contador++}")
    Log.d("TAG", "Timer-Fecha inicial: ${Calendar.getInstance().timeInMillis}")
    Log.d("TAG", "Timer-Fecha final: $finishTimeMillis")



    LaunchedEffect(key1 = viewModel.timerState) {
        // Launch a coroutine to update the timer every second if it's running
        while (true) {
            //Log.d("TAG", "Timer-Estoy en el while y IsRunning: ${viewModel.isRunning}")
            if (viewModel.isRunning) {
                //Log.d("TAG", "Timer-Estoy en el if")
                delay(1000)
                scope.launch {
                    val calendar = Calendar.getInstance()
                    //val currentTimeMillis = calendar.timeInMillis

                    val elapsedTimeMillis = finishTimeMillis - actualDate
                    Log.d("TAG", "Variable actualDate en el MainActivity: $actualDate")
                    var tiempo = calcularTiempoRestante(finishTimeMillis, actualDate)
                    //var tiempo = convertirMilisegundosADiasHorasMinutosSegundos(finishTimeMillis/1000)
                    //Log.d("TAG", "Fecha inicial: $currentTimeMillis")
                    Log.d("TAG", "Tiempo = $tiempo")


                    //val elapsedTimeMillis = (currentTimeMillis - finishTimeMillis) * 1000


                    /*val totalSeconds = elapsedTimeMillis / 1000
                    viewModel.timerState.days = totalSeconds / (3600 * 24)
                    viewModel.timerState.hours = (totalSeconds % (3600 * 24)) / 3600
                    viewModel.timerState.minutes = ((totalSeconds % (3600 * 24)) % 3600) / 60
                    viewModel.timerState.seconds = ((totalSeconds % (3600 * 24)) % 3600) % 60*/

                    /*viewModel.timerState.days = TimeUnit.MILLISECONDS.toDays(elapsedTimeMillis)
                    viewModel.timerState.hours = TimeUnit.MILLISECONDS.toHours(elapsedTimeMillis) % 24
                    viewModel.timerState.minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTimeMillis) % 60
                    viewModel.timerState.seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis) % 60*/

                    /*val temp = TimeUnit.MILLISECONDS.toDays(elapsedTimeMillis)
                    Log.d("TAG", "Timer-IF-Fecha inicial: ${Calendar.getInstance().timeInMillis}")
                    Log.d("TAG", "Timer-IF-Fecha final: $finishTimeMillis")
                    Log.d("TAG", "Timer-IF-Fecha final: $elapsedTimeMillis")
                    Log.d("TAG", "Days: $temp")
                    Log.d("TAG", "Hours: ${viewModel.timerState.hours}")
                    Log.d("TAG", "Minutes: ${viewModel.timerState.minutes}")
                    Log.d("TAG", "Seconds: ${viewModel.timerState.seconds}")*/
                }
            } else {
                // Sleep for a while when the timer is not running to save CPU usage
                delay(100)
            }
        }
    }
}

fun calcularTiempoRestante(milisegundos1: Long, milisegundos2: Long): String {
    val diferenciaMillis = if (milisegundos2 > milisegundos1) milisegundos2 - milisegundos1 else 0
    val segundosTotales = diferenciaMillis / 1000
    val segundos = segundosTotales % 60
    val minutosTotales = segundosTotales / 60
    val minutos = minutosTotales % 60
    val horasTotales = minutosTotales / 60
    val horas = horasTotales % 24
    val dias = horasTotales / 24
    return "$dias, $horas, $minutos, $segundos"
}

fun convertirMilisegundosADiasHorasMinutosSegundos(milisegundos: Long): String {
    /*val segundosTotales = milisegundos / 1000
    val segundos = segundosTotales % 60
    val minutosTotales = segundosTotales / 60
    val minutos = minutosTotales % 60
    val horasTotales = minutosTotales / 60
    val horas = horasTotales % 24
    val dias = horasTotales / 24*/

    val dias = TimeUnit.MILLISECONDS.toDays(milisegundos)
    val horas = TimeUnit.MILLISECONDS.toHours(milisegundos) % 24
    val minutos = TimeUnit.MILLISECONDS.toMinutes(milisegundos) % 60
    val segundos = TimeUnit.MILLISECONDS.toSeconds(milisegundos) % 60
    return "$dias, $horas, $minutos, $segundos"
}