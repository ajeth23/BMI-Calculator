package com.ecuacion.bmi_exer2


fun calculateBMI(weight: String, height: String): Pair<String, String>{

    val heightInMeters = height.toFloat() / 100
    val bmi = weight.toFloat() / (heightInMeters * heightInMeters)
    val bmiScore = bmi.toString()

    var bmiResult = when {
        bmi < 18.4 -> "Underweight"
        bmi < 25.0 -> "Normal"
        bmi < 39.9 -> "Overweight"
        else -> "Obese"
    }

    return Pair(bmiScore, bmiResult)
}