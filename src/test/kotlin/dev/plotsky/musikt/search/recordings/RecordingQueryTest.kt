package dev.plotsky.musikt.search.recordings

import org.junit.Test
import kotlin.test.assertEquals

class RecordingQueryTest {
    @Test
    fun testGetQuery() {
        val query =
            RecordingQuery(
                artist = "The Sword"
            )
        assertEquals("artist:The Sword", query.getQuery())
    }

    @Test
    fun testGetQueryAllTerms() {
        val query =
            RecordingQuery(
                artist = "The Sword",
                title = "Used Future"
            )
        assertEquals("artist:The Sword AND title:Used Future", query.getQuery())
    }

    @Test
    fun testGetEncodedQueryReplacedValues() {
        val query =
            RecordingQuery(
                artist = "\"Zeal & Ardor\"",
                title = "Gravedigger's Chant"
            )
        assertEquals(
            "artist:%22Zeal+and+Ardor%22+AND+title:Gravedigger+Chant",
            query.getEncodedQuery()
        )
    }
}
