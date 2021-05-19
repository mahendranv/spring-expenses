package com.ex2.gql.expense.fetchers

import com.ex2.gql.expense.data.DataSource
import com.ex2.gql.expense.data.models.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class AccountDataFetcher {

    @DgsQuery
    fun accounts(): List<Account> {
        return DataSource.accounts
    }

    @DgsMutation
    fun deleteAccount(acNumber: Int): Boolean {
        return DataSource.accounts.removeIf { it.acNumber == acNumber }
    }

    @DgsMutation
    fun updateAccount(account: Account): Account {
        val index = DataSource.accounts.indexOfFirst { it.acNumber == account.acNumber }
        if (index == -1) {
            DataSource.accounts.add(0, account)
        } else {
            DataSource.accounts[index] = account
        }
        return account
    }
}