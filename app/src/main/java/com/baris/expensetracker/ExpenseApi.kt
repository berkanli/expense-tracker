package com.baris.expensetracker

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExpenseApi {
    @GET("api/expenses")
    fun getAllExpenses(): Call<List<Expense>>

    @POST("api/expenses")
    fun addExpense(@Body expense: Expense): Call<Expense>
}