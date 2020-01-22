package dev.plotsky.musikt.search

interface EntityRepository<T> {
    fun getItemById(
        endpoint: String,
        idOptions: IdOptions
    ): T?
}
