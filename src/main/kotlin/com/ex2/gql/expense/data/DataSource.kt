package com.ex2.gql.expense.data

import com.ex2.gql.expense.data.models.Account
import com.ex2.gql.expense.data.models.Expense


object DataSource {

    val accounts = mutableListOf(
        Account(acNumber = 1, nickName = "Axis", balance = 100000),
        Account(acNumber = 2, nickName = "ICICI", balance = 240000),
    )

    val expenses = mutableListOf(
        Expense(id = 1, amount = 10000, remarks = "Rent", isIncome = false, acNumber = 1),
        Expense(id = 2, amount = 4000, remarks = "Bills", isIncome = false, acNumber = 1),
        Expense(id = 2, amount = 1000, remarks = "Books", isIncome = false, acNumber = 2),
    )

    object DAO {

        fun getAccount(acNumber: Int): Account {
            println("Accounts.getAccount > $acNumber")
            return accounts.find { it.acNumber == acNumber }!!
        }

    }
}