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

package es.acracksoft.moticount.datos

import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import java.util.Calendar

//Función que calcula el tiempo transcurrido entre dos fechas
@Composable
fun calculateTimeDifference(
    //añadido
    calendar: Calendar,
    scope: CoroutineScope,
    isRunning: Boolean,
    //añadido
    initDate: Long,
    endDate: Long,
    dia: (Long) -> Unit,
    hora: (Long) -> Unit,
    minuto: (Long) -> Unit,
    segundo: (Long) -> Unit,
    final: (Boolean) -> Unit,
) {
    /*val timeDifference = endDate - initDate

    val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
    val hours = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60

    //Actualizamos las variables de tiempo
    days.also(dia)
    hours.also(hora)
    minutes.also(minuto)
    seconds.also(segundo)*/
    Log.d("TAG", "Fecha inicial: $initDate")
    Log.d("TAG", "Fecha final: $endDate")
    Log.d(
        "TAG",
        "Tiempo dentro del scope: Days: $dia, Hours: ${hora}, Minutes: $minuto, Seconds: $segundo"
    )

    /////
    /*LaunchedEffect(
        key1  = isRunning
    ) {
        while (true) {
            if (isRunning) {
                scope.launch  {
                    val timeDifference = endDate - initDate

                    val days = TimeUnit.MILLISECONDS.toDays(timeDifference)
                    val hours = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifference) % 60

                    //Actualizamos las variables de tiempo
                    days.also(dia)
                    hours.also(hora)
                    minutes.also(minuto)
                    seconds.also(segundo)

                    if (days == 0L && hours == 0L && minutes == 0L && seconds == 0L) {
                        final(true)
                    }

                    Log.d(
                        "TAG",
                        "Tiempo dentro del scope: Days: $days, Hours: ${hours - 2}, Minutes: $minutes, Seconds: $seconds"
                    )
                }//scope
            }//isRunning
            else {
                // Sleep for a while when the timer is not running to save CPU usage
                delay(100)
            }
        }
    }//launchedEffect

*/
}