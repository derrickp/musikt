package dev.plotsky.musikt.search

interface ListRepository<T> {
    fun byTerm(term: String, endpoint: String): T?
}
