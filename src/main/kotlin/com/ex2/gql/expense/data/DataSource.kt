package com.ex2.gql.expense.data

import com.ex2.gql.expense.fetchers.AccountDataFetcher
import com.ex2.gql.expense.fetchers.ExpenseDataFetcher

object DataSource {

    val accounts = mutableListOf<AccountDataFetcher.Account>()

    val expenses = mutableListOf<ExpenseDataFetcher.Expense>()
}