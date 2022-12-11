package com.test.boobluk.utils.int

fun differenceBetweenTwoNumbers(
    firstNumber: Int,
    secondNumber: Int
) = (firstNumber + secondNumber) / 2

fun checkIfNumberIsInRange(
    range: IntRange,
    number: Int
) = (number > range.first && number < range.last)