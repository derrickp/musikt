package dev.plotsky.musikt.search

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plotsky.musikt.Request
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MusicbrainzListSearch<T>(
    private val klass: Class<T>,
    private val request: Request
) : ListSearch<T> {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun byTerm(endpoint: String, term: String): T? {
        val parameters = termParameters(
            URLEncoder.encode(term, StandardCharsets.UTF_8)
        )
        return get(endpoint, parameters)
    }

    override fun byQuery(endpoint: String, query: Query): T? {
        val parameters = queryParameters(query.getEncodedQuery())
        return get(endpoint, parameters)
    }

    private fun get(endpoint: String, parameters: Map<String, String>): T? {
        val response = request.get(
            endpoint,
            parameters
        )

        return if (response.body != null) {
            buildClass(response.body!!.string())
        } else {
            null
        }
    }

    private fun buildClass(json: String): T? {
        return try {
            val adapter = moshi.adapter(klass)
            adapter.fromJson(json)
        } catch (e: JsonDataException) {
            null
        }
    }

    private fun queryParameters(query: String): Map<String, String> {
        return mapOf("fmt" to "json", "query" to query)
    }

    private fun termParameters(term: String): Map<String, String> {
        return mapOf("fmt" to "json", "query" to term)
    }
}
