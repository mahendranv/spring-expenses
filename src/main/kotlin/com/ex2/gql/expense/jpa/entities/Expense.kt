package com.ex2.gql.expense.jpa.entities

import javax.persistence.*

@Entity(name = "expenses")
data class Expense(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, name = "amount")
    val amount: Int = 0,

    @Column(name = "remarks", nullable = false)
    val remarks: String = "",

    @Column(name = "is_income", nullable = false)
    val isIncome: Boolean = false,

    @Column(name = "account_number", nullable = false)
    val acNumber: Int = 0
)
