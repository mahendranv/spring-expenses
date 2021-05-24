package com.ex2.gql.expense.fetchers

import com.ex2.gql.expense.jpa.entities.Expense
import com.ex2.gql.expense.jpa.repo.ExpenseRepository
import com.netflix.graphql.dgs.*
import org.springframework.beans.factory.annotation.Autowired
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    @Autowired
    private lateinit var expenseRepository: ExpenseRepository


    @DgsData(parentType = "Query", field = "expenses")
    fun expenses(dfe: DgsDataFetchingEnvironment): List<Expense> {
        return expenseRepository.findAll().toList()
    }

    private val random = Random(10000)
    private fun randomInt() = random.nextInt()

    @DgsData(parentType = "Mutation", field = "createExpense")
    fun createExpense(@InputArgument("data") data: Expense): Expense {
        return expenseRepository.save(data)
    }

    @DgsData(parentType = "Mutation", field = "deleteExpense")
    fun deleteExpense(@InputArgument("id") id: Int): Boolean {
        expenseRepository.deleteById(id)
        return true
    }

    @DgsData(parentType = "Mutation", field = "updateExpense")
    fun updateExpense(@InputArgument("expense") expense: Expense): Expense {
        return expenseRepository.save(expense)
    }
}