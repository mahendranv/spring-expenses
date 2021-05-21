package com.ex2.gql.expense.data

import com.ex2.gql.expense.data.models.Account
import com.ex2.gql.expense.data.models.Expense
import kotlin.math.absoluteValue
import kotlin.random.Random


object DataSource {

    val accounts = mutableListOf(
        Account(acNumber = 1, nickName = "Wallet", balance = 100000),
        Account(acNumber = 2, nickName = "Axis", balance = 240000),
        Account(acNumber = 3, nickName = "ICICI", balance = 28000),
    )

    val expenses = mutableListOf<Expense>()

    private val random = Random(10000)
    private fun randomExpense() = random.nextInt(from = 100, until = 800)

    init {
        for (i in 1..100) {
            expenses.add(
                Expense(
                    id = i,
                    amount = randomExpense().absoluteValue,
                    remarks = "Transaction $i",
                    isIncome = (i % 2 == 0),
                    acNumber = (i % 3 + 1)
                )
            )
        }
    }

    object DAO {

        fun getAccount(acNumber: Int): Account {
            println("Accounts.getAccount > $acNumber")
            return accounts.find { it.acNumber == acNumber }!!
        }

        fun getAccounts(acNumbers: List<Int>): List<Account> {
            println("DAO.getAccounts - $acNumbers")
            return accounts.filter {
                acNumbers.contains(it.acNumber)
            }
        }
    }
}