package com.ex2.gql.expense.adapters

import com.ex2.gql.expense.jpa.entities.Expense as EExpense
import com.ex2.gql.dgmodels.types.Expense as GExpense

object ExpenseGEMapper : GraphEntityMapper<GExpense, EExpense> {

    override fun toGraph(e: EExpense): GExpense {
        return GExpense(
            id = e.id?.toString(),
            remarks = e.remarks,
            acNumber = e.acNumber,
            isIncome = e.isIncome,
            amount = e.amount,
            account = null
        )
    }

    override fun toEntity(g: GExpense): EExpense {
        return EExpense(
            id = g.id?.toInt(),
            remarks = g.remarks,
            acNumber = g.acNumber,
            isIncome = g.isIncome,
            amount = g.amount
        )
    }
}