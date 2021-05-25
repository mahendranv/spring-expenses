package com.ex2.gql.expense.fetchers

import com.ex2.gql.dgmodels.types.Expense
import com.ex2.gql.expense.adapters.AccountGEMapper
import com.ex2.gql.expense.jpa.entities.Account
import com.ex2.gql.expense.jpa.repo.AccountRepository
import com.ex2.gql.expense.loaders.AccountsDataLoader
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.InputArgument
import org.dataloader.DataLoader
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture

@DgsComponent
class AccountDataFetcher {

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @DgsData(parentType = "Query", field = "accounts")
    fun accounts(): List<com.ex2.gql.dgmodels.types.Account> {
        return accountRepository.findAll().map { AccountGEMapper.toGraph(it) }
    }

    @DgsData(parentType = "Mutation", field = "deleteAccount")
    fun deleteAccount(@InputArgument("acNumber") acNumber: Int): Boolean {
        accountRepository.deleteById(acNumber)
        return true //DataSource.accounts.removeIf { it.acNumber == acNumber }
    }

    @DgsData(parentType = "Mutation", field = "updateAccount")
    fun updateAccount(@InputArgument("account") account: com.ex2.gql.dgmodels.types.Account): com.ex2.gql.dgmodels.types.Account {
        return AccountGEMapper.toGraph(accountRepository.save(AccountGEMapper.toEntity(account)))
    }

    @DgsData(parentType = "Expense", field = "account")
    fun getAccounts(dfe: DgsDataFetchingEnvironment): CompletableFuture<com.ex2.gql.dgmodels.types.Account> {
        val dataLoader: DataLoader<Int, com.ex2.gql.dgmodels.types.Account> =
            dfe.getDataLoader(AccountsDataLoader::class.java)
        val source = dfe.getSource<Expense>()
        val acNumber = source.acNumber
        return dataLoader.load(acNumber)
    }
}