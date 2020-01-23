package dev.plotsky.musikt.search

interface Repository<T> {
    fun getById(idOptions: IdOptions): T?
    fun getByTerm(term: String): List<T>
    fun getByQuery(query: Query): List<T>
}