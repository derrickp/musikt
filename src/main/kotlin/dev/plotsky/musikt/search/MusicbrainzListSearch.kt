package dev.plotsky.musikt.search

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plotsky.musikt.Request
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MusicbrainzListSearch<T>(
    private val klass: Class<T>,
    private val request: Request,
    private val adapters: List<Any> = emptyList()
) {
    private val moshi: Moshi? = null

    private fun getMoshi(): Moshi {
        return moshi ?: buildMoshi()
    }

    private fun buildMoshi(): Moshi {
        val builder = Moshi.Builder()
        for (adapter in adapters) {
            builder.add(adapter)
        }
        builder.add(KotlinJsonAdapterFactory())
        return builder.build()
    }

    fun byTerm(endpoint: String, term: String): T? {
        val parameters = termParameters(
            URLEncoder.encode(term, StandardCharsets.UTF_8)
        )
        return get(endpoint, parameters)
    }

    fun byQuery(endpoint: String, query: Query): T? {
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
            val adapter = getMoshi().adapter(klass)
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
