package com.ex2.gql.expense.fetchers

import com.ex2.gql.expense.data.DataSource
import com.ex2.gql.expense.data.models.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class AccountDataFetcher {

    @DgsData(parentType = "Query", field = "accounts")
    fun accounts(): List<Account> {
        return DataSource.accounts
    }

    @DgsData(parentType = "Mutation", field = "deleteAccount")
    fun deleteAccount(@InputArgument("acNumber") acNumber: Int): Boolean {
        return DataSource.accounts.removeIf { it.acNumber == acNumber }
    }

    @DgsData(parentType = "Mutation", field = "updateAccount")
    fun updateAccount(@InputArgument("account") account: Account): Account {
        val index = DataSource.accounts.indexOfFirst { it.acNumber == account.acNumber }
        if (index == -1) {
            DataSource.accounts.add(0, account)
        } else {
            DataSource.accounts[index] = account
        }
        return account
    }
}