package dev.plotsky.musikt.search

interface Query {
    fun getQuery(): String
    fun getEncodedQuery(): String
}
