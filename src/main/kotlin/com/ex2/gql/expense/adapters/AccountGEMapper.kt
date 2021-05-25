package com.ex2.gql.expense.adapters

import com.ex2.gql.dgmodels.types.Account as GAccount
import com.ex2.gql.expense.jpa.entities.Account as EAccount

object AccountGEMapper : GraphEntityMapper<GAccount, EAccount> {

    override fun toGraph(e: EAccount): GAccount {
        return GAccount(
            acNumber = e.acNumber.toString(),
            nickName = e.nickName,
            balance = e.balance
        )
    }

    override fun toEntity(g: GAccount): EAccount {
        return EAccount(
            acNumber = g.acNumber?.toInt(),
            nickName = g.nickName!!,
            balance = g.balance!!
        )
    }
}