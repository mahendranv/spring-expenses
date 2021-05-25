package com.ex2.gql.expense.jpa.entities

import javax.persistence.*

@Entity(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "account_number")
    val acNumber: Int? = null,

    @Column(nullable = false, name = "nick_name")
    val nickName: String = "",

    @Column(nullable = false, name = "balance")
    val balance: Int = 0
)
