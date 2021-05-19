package com.ex2.gql.expense.fetchers

import com.ex2.gql.expense.data.DataSource
import com.ex2.gql.expense.data.models.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    @DgsQuery
    fun expenses(): List<FatExpense> {
        return DataSource.expenses.map {
            FatExpense(
                id = it.id,
                amount = it.amount,
                remarks = it.remarks,
                isIncome = it.isIncome,
                account = DataSource.DAO.getAccount(it.acNumber)
            )
        }
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
            acNumber = data.acNumber,
        )
        DataSource.expenses.add(0, expense)
        return expense
    }

    @DgsMutation
    fun deleteExpense(id: Int): Boolean {
        return DataSource.expenses.removeIf { it.id == id }
    }

    @DgsMutation
    fun updateExpense(expense: Expense): Expense {
        // This is an upsert operation. If id already present in list
        // overwrite it / otherwise insert at 0th index
        val index = DataSource.expenses.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            DataSource.expenses[index] = expense
        } else {
            DataSource.expenses.add(0, expense)
        }
        return expense
    }
}