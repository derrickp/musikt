package dev.plotsky.musikt

import org.junit.Test
import kotlin.test.assertEquals

class ClientTest {
    @Test
    fun basicClientTest() {
        val config = buildDefaultConfiguration()
        val client = Client.build(config)
        val artists = client.artists.getByTerm("Iron Maiden")
        assertEquals(25, artists.size)
    }
}
