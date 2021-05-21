package com.ex2.gql.expense.loaders

import com.ex2.gql.expense.data.DataSource
import com.ex2.gql.expense.data.models.Account
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.BatchLoader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "AccountNumberToAccount")
class AccountsDataLoader : BatchLoader<Int, Account> {

    override fun load(keys: MutableList<Int>?)
            : CompletionStage<MutableList<Account>> {
        return CompletableFuture.supplyAsync {
            return@supplyAsync DataSource.DAO
                .getAccounts(keys ?: emptyList())
                .toMutableList()
        }
    }
}