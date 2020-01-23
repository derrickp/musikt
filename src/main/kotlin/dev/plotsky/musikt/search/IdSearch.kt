package dev.plotsky.musikt.search

interface IdSearch<T> {
    fun getItemById(
        endpoint: String,
        idOptions: IdOptions
    ): T?
}
