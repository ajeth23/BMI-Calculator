package com.ecuacion.bmi_exer2.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecuacion.bmi_exer2.calculateBMI


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInterface() {

    var weight by remember { mutableStateOf("") } //outlinedTextfield for weight
    var height by remember { mutableStateOf("") } //outlinedTextfield for height

    var bmiScore by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }

    val customColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = Color.Black,        // Text color
        cursorColor = Color.Black,      // Cursor color
        focusedBorderColor = Color.Blue, // Border color when focused
        unfocusedBorderColor = Color.Gray // Border color when not focused
    )

    val context = LocalContext.current

    val tableData = listOf(
        "18.4" to "Underweight",
        "18.5 - 24.9" to "Normal",
        "25.0 - 39.9" to "Overweight",
        "40" to "Obese"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "BMI Calculator", fontSize = 25.sp)

            //Input weight
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = weight,
                onValueChange = {
                    weight = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                label = { Text(text = "Enter Weight in kg") },
                colors = customColors
            )


            //input height
            OutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done,
                ),
                label = { Text(text = "Enter height in cm") },
                colors = customColors
            )


            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (weight.isEmpty() || height.isEmpty()) {
                    Toast.makeText(context, "Please complete the fields", Toast.LENGTH_SHORT).show()
                } else {
                    val (score, result) = calculateBMI(weight, height)
                    bmiScore = score
                    bmiResult = result
                }

            }) {
                Text(text = "Calculate BMI")
            }



            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.height(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Your score: $bmiScore", fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Your body is $bmiResult", fontSize = 20.sp)
                }
            }



            Spacer(modifier = Modifier.height(30.dp))
            val column1Weight = .5f // 50%
            val column2Weight = .5f // 50%
            // The LazyColumn will be our table. Notice the use of the weights below
            LazyColumn(
                Modifier
                    .fillMaxSize() 
                    .padding(16.dp)
            ) {
                // Here is the header
                item {
                    Row(
                        Modifier.background(Color.Gray)
                    ) {
                        TableCell(text = "BMI", weight = column1Weight)
                        TableCell(text = "Status", weight = column2Weight)
                    }
                }
                // Here are all the lines of your table.
                items(tableData) {
                    val (id, text) = it
                    Row(
                        Modifier.fillMaxWidth()
                    ) {
                        TableCell(text = id, weight = column1Weight)
                        TableCell(text = text, weight = column2Weight)
                    }
                }
            }


        }
    }
}


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            //    .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp),
        textAlign = TextAlign.Center, // Align text item
    )
}