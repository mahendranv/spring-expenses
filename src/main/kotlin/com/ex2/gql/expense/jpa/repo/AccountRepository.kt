package com.ex2.gql.expense.jpa.repo

import com.ex2.gql.expense.jpa.entities.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Int>
