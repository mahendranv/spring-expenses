package com.ex2.gql.expense.data.models

data class ExpenseInput(
    val amount: Int,
    val remarks: String,
    val isIncome: Boolean,
    val acNumber: Int
)

data class Expense(
    val id: Int,
    val amount: Int,
    val remarks: String,
    val isIncome: Boolean,
    val acNumber: Int
)