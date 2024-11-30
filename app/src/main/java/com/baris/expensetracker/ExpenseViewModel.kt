package com.baris.expensetracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpenseViewModel : ViewModel() {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    fun getAllExpenses() {
        viewModelScope.launch {
            RetrofitClient.instance.getAllExpenses().enqueue(object : Callback<List<Expense>> {
                override fun onResponse(call: Call<List<Expense>>, response: Response<List<Expense>>) {
                    if (response.isSuccessful) {
                        _expenses.addAll(response.body() ?: emptyList())
                    }
                }

                override fun onFailure(call: Call<List<Expense>>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            RetrofitClient.instance.addExpense(expense).enqueue(object : Callback<Expense> {
                override fun onResponse(call: Call<Expense>, response: Response<Expense>) {
                    if (response.isSuccessful) {
                        // Add the expense to the list
                        _expenses.add(response.body() ?: return)
                    }
                }

                override fun onFailure(call: Call<Expense>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}