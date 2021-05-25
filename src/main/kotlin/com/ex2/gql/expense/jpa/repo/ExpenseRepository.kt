package com.ex2.gql.expense.jpa.repo

import com.ex2.gql.expense.jpa.entities.Expense
import org.springframework.data.jpa.repository.JpaRepository

interface ExpenseRepository : JpaRepository<Expense, Int>