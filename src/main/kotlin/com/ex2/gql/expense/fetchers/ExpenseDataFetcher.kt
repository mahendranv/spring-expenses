package com.ex2.gql.expense.fetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    private val expenses = mutableListOf(
        Expense(id = 1, amount = 16000, remarks = "Rent", isIncome = false),
        Expense(id = 2, amount = 100000, remarks = "Salary", isIncome = false),
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

    @DgsMutation
    fun deleteExpense(id: Int): Boolean {
        return expenses.removeIf { it.id == id }
    }

    @DgsMutation
    fun updateExpense(expense: Expense): Expense {
        // This is an upsert operation. If id already present in list
        // overwrite it / otherwise insert at 0th index
        val index = expenses.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            expenses[index] = expense
        } else {
            expenses.add(0, expense)
        }
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