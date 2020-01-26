package dev.plotsky.musikt.search

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class RecordingQuery(val artist: String?, val title: String? = null) : Query {
    override fun getQuery(): String {
        val terms = mapOf("artist" to artist, "title" to title)
            .filterValues { it != null }
        return terms.map { "${it.key}:${it.value}" }.joinToString(" AND ")
    }

    override fun getEncodedQuery(): String {
        val terms = mapOf("artist" to artist, "title" to title)
            .filterValues { it != null }
        val queryTerms = terms
            .map { "${it.key}:${getEncodedValue(it.value!!)}" }
        return queryTerms.joinToString("+AND+")
    }

    private fun getEncodedValue(value: String): String {
        val replaced = value
            .replace("&", "and")
            .replace("'s", "")
        return URLEncoder.encode(replaced, StandardCharsets.UTF_8)
    }
}
