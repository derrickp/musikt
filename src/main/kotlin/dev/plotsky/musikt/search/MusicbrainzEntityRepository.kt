package dev.plotsky.musikt.search

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plotsky.musikt.Request

class MusicbrainzEntityRepository<T>(
    private val klass: Class<T>,
    private val request: Request
) : EntityRepository<T> {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun getItemById(
        endpoint: String,
        idOptions: IdOptions
    ): T? {
        val json = get(endpoint, idOptions)
        val adapter = moshi.adapter(klass)
        return if (json != null) adapter.fromJson(json) else null
    }

    private fun get(endpoint: String, idOptions: IdOptions): String? {
        val response = request.get(
            "$endpoint/${idOptions.id}",
            parameters(idOptions)
        )
        return response.body?.string()
    }

    private fun parameters(idOptions: IdOptions): Map<String, String> {
        val formatParams = mapOf("fmt" to "json")
        return if (idOptions.relationships.isEmpty()) {
            formatParams
        } else {
            val inc = idOptions.relationships.joinToString("")
            formatParams + Pair("inc", inc)
        }
    }
}
