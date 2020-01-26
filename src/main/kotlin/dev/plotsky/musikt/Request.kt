package dev.plotsky.musikt

import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest
import okhttp3.Response

class Request(
    private val config: Configuration,
    private val client: OkHttpClient
) {
    fun get(
        endpoint: String,
        encodedParameters: Map<String, String>
    ): Response {
        val urlQuery: String = encodedParameters.entries.joinToString("&") {
            "${it.key}=${it.value}"
        }
        val url = "${config.baseUrl}/$endpoint?$urlQuery"
        val request = OkHttpRequest.Builder().url(url).build()
        return client.newCall(request).execute()
    }
}

fun buildRequest(config: Configuration): Request {
    val client = OkHttpClient().newBuilder().build()
    return Request(config, client)
}
