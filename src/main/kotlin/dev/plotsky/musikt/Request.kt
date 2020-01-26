package dev.plotsky.musikt

import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import okhttp3.OkHttpClient
import okhttp3.Request as OkHttpRequest
import okhttp3.Response

class Request(
    private val config: Configuration,
    private val client: OkHttpClient
) {
    fun get(endpoint: String, parameters: Map<String, String>): Response {
        val encodedParams: String = parameters.entries.joinToString("&") {
            "${it.key}=${URLEncoder.encode(it.value, StandardCharsets.UTF_8)}"
        }
        val url = "${config.baseUrl}/$endpoint?$encodedParams"
        val request = OkHttpRequest.Builder().url(url).build()
        return client.newCall(request).execute()
    }
}

fun buildRequest(config: Configuration): Request {
    val client = OkHttpClient().newBuilder().build()
    return Request(config, client)
}
