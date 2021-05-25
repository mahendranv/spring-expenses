package com.ex2.gql.expense.fetchers

import com.ex2.gql.dgmodels.types.ExpenseFilter
import com.ex2.gql.dgmodels.types.ExpensePage
import com.ex2.gql.expense.adapters.ExpenseGEMapper
import com.ex2.gql.expense.jpa.entities.Expense
import com.ex2.gql.expense.jpa.repo.ExpenseRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import kotlin.random.Random

@DgsComponent
class ExpenseDataFetcher {

    @Autowired
    private lateinit var expenseRepository: ExpenseRepository


    @DgsData(parentType = "Query", field = "expenses")
    fun expenses(
        @InputArgument("filter") filter: ExpenseFilter?,
        @InputArgument("pageNumber") pageNumber: Int,
        @InputArgument("pageSize") pageSize: Int
    ): ExpensePage {

        val probe = Expense(
            id = null,
            remarks = null,
            acNumber = filter?.acNumber?.toInt(),
            isIncome = filter?.isIncome,
            amount = null
        )
        val example = Example.of(probe)
        val pageRequest = PageRequest.of(
            pageNumber, pageSize, Sort.by(
                Sort.Order.desc("id")
            )
        )

        val result = expenseRepository.findAll(example, pageRequest)
        val list = result.content.map {
            ExpenseGEMapper.toGraph(it)
        }

        return ExpensePage(
            list = list,
            totalPages = result.totalPages,
            currentPage = pageNumber
        )
    }

    private val random = Random(10000)
    private fun randomInt() = random.nextInt()

    @DgsData(parentType = "Mutation", field = "createExpense")
    fun createExpense(@InputArgument("data") data: com.ex2.gql.dgmodels.types.Expense): com.ex2.gql.dgmodels.types.Expense {
        return ExpenseGEMapper.toGraph(
            expenseRepository.save(ExpenseGEMapper.toEntity(data))
        )
    }

    @DgsData(parentType = "Mutation", field = "deleteExpense")
    fun deleteExpense(@InputArgument("id") id: Int): Boolean {
        expenseRepository.deleteById(id)
        return true
    }

    @DgsData(parentType = "Mutation", field = "updateExpense")
    fun updateExpense(@InputArgument("expense") expense: com.ex2.gql.dgmodels.types.Expense): com.ex2.gql.dgmodels.types.Expense {
        return ExpenseGEMapper.toGraph(
            expenseRepository.save(ExpenseGEMapper.toEntity(expense))
        )
    }
}