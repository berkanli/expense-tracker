package com.baris.expensetracker

data class Expense(
    val id: Long,
    val description: String,
    val amount: Double,
    val date: String
)