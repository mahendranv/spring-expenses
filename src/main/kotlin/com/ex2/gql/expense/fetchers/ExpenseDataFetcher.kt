package com.ex2.gql.expense.fetchers

import com.ex2.gql.expense.data.DataSource
import com.ex2.gql.expense.data.models.*
import com.netflix.graphql.dgs.*
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    @DgsData(parentType = "Query", field = "expenses")
    fun expenses(dfe: DgsDataFetchingEnvironment): List<Expense> {
        return DataSource.expenses
    }

    private val random = Random(10000)
    private fun randomInt() = random.nextInt()

    @DgsData(parentType = "Mutation", field = "createExpense")
    fun createExpense(@InputArgument("data") data: ExpenseInput): Expense {
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

    @DgsData(parentType = "Mutation", field = "deleteExpense")
    fun deleteExpense(@InputArgument("id") id: Int): Boolean {
        return DataSource.expenses.removeIf { it.id == id }
    }

    @DgsData(parentType = "Mutation", field = "updateExpense")
    fun updateExpense(@InputArgument("expense") expense: Expense): Expense {
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