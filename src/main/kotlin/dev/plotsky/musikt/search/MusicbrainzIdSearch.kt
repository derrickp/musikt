package dev.plotsky.musikt.search

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plotsky.musikt.Request

class MusicbrainzIdSearch<T>(
    private val klass: Class<T>,
    private val request: Request,
    private val adapters: List<Any> = emptyList()
) : IdSearch<T> {
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

    override fun getItemById(
        endpoint: String,
        idOptions: IdOptions
    ): T? {
        val json = get(endpoint, idOptions)
        val adapter = getMoshi().adapter(klass)
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
