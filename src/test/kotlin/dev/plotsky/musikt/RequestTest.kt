package dev.plotsky.musikt

import kotlin.test.assertEquals
import okreplay.OkReplay
import okreplay.TapeMode
import org.junit.Test

class RequestTest : ReplayTest(TapeMode.READ_ONLY_QUIET) {
    @Test @OkReplay
    fun testGetMethod() {
        val parameters = mapOf(
            "query" to "artist:\"The Sword\" AND title:Used Future",
            "fmt" to "json"
        )
        val endpoint = "recording"
        val config = buildDefaultConfiguration()
        val request = Request(config, client)
        val response = request.get(endpoint, parameters)
        assertEquals(200, response.code)
    }
}
