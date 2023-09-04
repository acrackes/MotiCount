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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
                false.also(onShowDatePickerChange)
            },
            confirmButton = {
                TextButton(onClick = {
                    false.also(onShowDatePickerChange)
                    datePickerState.selectedDateMillis!!.also(onSelectedDateChange)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    false.also(onShowDatePickerChange)
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

    ExtendedFloatingActionButton(
        //modifier = Modifier.padding(64.dp),
        onClick = { true.also(onShowDatePickerChange) },
        icon = { Icon(Icons.Filled.Add, "Seleccione fecha") },
        text = { Text(text = "Seleccione fecha") },
    )

    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)
    Text(
        text = "Selected date: ${formatter.format(Date(selectedDate))}"
    )
    //Log.d("TAG", "Selected date: $selectedDate")

}