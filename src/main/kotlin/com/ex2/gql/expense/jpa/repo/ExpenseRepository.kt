package com.ex2.gql.expense.jpa.repo

import com.ex2.gql.expense.jpa.entities.Expense
import org.springframework.data.repository.CrudRepository

interface ExpenseRepository : CrudRepository<Expense, Int>