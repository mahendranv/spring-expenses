package com.ex2.gql.expense.fetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    private val expenses = mutableListOf(
        Expense(id = 1, amount = 10, remarks = "Expense 1", isIncome = false),
        Expense(id = 2, amount = 120, remarks = "Expense 1", isIncome = false),
        Expense(id = 3, amount = 110, remarks = "Expense 1", isIncome = false),
        Expense(id = 4, amount = 30, remarks = "Expense 1", isIncome = false),
        Expense(id = 5, amount = 70, remarks = "Expense 1", isIncome = false),
        Expense(id = 6, amount = 90, remarks = "Income 1", isIncome = true),
    )

    @DgsQuery
    fun expenses(): List<Expense> {
        return expenses
    }

    private val random = Random(10000)
    private fun randomInt() = random.nextInt()

    @DgsMutation
    fun createExpense(data: ExpenseInput): Expense {
        val expense = Expense(
            id = randomInt(),
            amount = data.amount,
            remarks = data.remarks,
            isIncome = data.isIncome,
        )
        expenses.add(0, expense)
        return expense
    }

    data class ExpenseInput(
        val amount: Int,
        val remarks: String,
        val isIncome: Boolean
    )

    data class Expense(
        val id: Int,
        val amount: Int,
        val remarks: String,
        val isIncome: Boolean
    )

}