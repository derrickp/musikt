package dev.plotsky.musikt.search

interface ListSearch<T> {
    fun byTerm(endpoint: String, term: String): T?
    fun byQuery(endpoint: String, query: Query): T?
}
