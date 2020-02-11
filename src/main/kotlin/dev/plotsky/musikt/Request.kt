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
        val request = buildRequest(url)
        return client.newCall(request).execute()
    }

    private fun buildRequest(url: String): okhttp3.Request {
        return OkHttpRequest.Builder()
            .addHeader("User-Agent", "${config.appName} ${config.contact}")
            .url(url)
            .build()
    }
}
