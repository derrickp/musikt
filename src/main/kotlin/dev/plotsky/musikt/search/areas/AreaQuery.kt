package dev.plotsky.musikt.search.areas

import dev.plotsky.musikt.search.Query
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class AreaQuery(
    val area: String? = null,
    val sortName: String? = null,
    val type: String? = null
) : Query {
    override fun getQuery(): String {
        TODO("not implemented")
    }

    override fun getEncodedQuery(): String {
        val terms = mapOf(
            "area" to area, "sortname" to sortName, "type" to type
        ).filterValues { it != null }
        val queryTerms = terms
            .map { "${it.key}:${getEncodedValue(it.value!!)}" }
        return queryTerms.joinToString("+AND+")
    }

    private fun getEncodedValue(value: String): String {
        val replaced = swapKnownProblemValues(value)
        return URLEncoder.encode(replaced, StandardCharsets.UTF_8)
    }

    private fun swapKnownProblemValues(value: String): String {
        return value
            .replace("&", "and")
            // These ones for some reason fail through the API
            // So we just swap them out for nothing and hope something is found
            .replace("'s", "")
            .replace("'t", "")
            .replace("'re", "")
            .replace("'d", "")
    }
}
