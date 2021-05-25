package com.ex2.gql.expense.loaders

import com.ex2.gql.expense.adapters.AccountGEMapper
import com.ex2.gql.expense.jpa.repo.AccountRepository
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.BatchLoader
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "AccountNumberToAccount")
class AccountsDataLoader : BatchLoader<Int, com.ex2.gql.dgmodels.types.Account> {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    override fun load(keys: MutableList<Int>?)
            : CompletionStage<MutableList<com.ex2.gql.dgmodels.types.Account>> {
        return CompletableFuture.supplyAsync {
            return@supplyAsync accountRepository
                .findAllById(keys!!.asIterable())
                .map { AccountGEMapper.toGraph(it) }
                .toMutableList()
        }
    }
}