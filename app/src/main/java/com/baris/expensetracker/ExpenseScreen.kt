package com.baris.expensetracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = viewModel()) {
    val expenses by remember { mutableStateOf(viewModel.expenses) }

    LaunchedEffect(Unit) {
        viewModel.getAllExpenses()
    }

    Column {
        Text("Expenses", style = MaterialTheme.typography.labelMedium)
        LazyColumn {
            items(expenses) { expense ->
                Text(text = "${expense.description}: \$${expense.amount}")
            }
        }

        Button(onClick = {
            val newExpense = Expense(0, "Lunch", 12.5, "2024-11-30")
            viewModel.addExpense(newExpense)
        }) {
            Text("Add Expense")
        }
    }
}
