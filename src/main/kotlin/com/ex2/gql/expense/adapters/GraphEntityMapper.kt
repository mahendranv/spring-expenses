package com.ex2.gql.expense.adapters

interface GraphEntityMapper<G, E> {

    fun toGraph(e: E): G

    fun toEntity(g: G): E
}