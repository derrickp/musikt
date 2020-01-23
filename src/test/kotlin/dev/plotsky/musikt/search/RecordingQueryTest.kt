package dev.plotsky.musikt.search

import kotlin.test.assertEquals
import org.junit.Test

class RecordingQueryTest {
    @Test
    fun testGetQuery() {
        val query = RecordingQuery(artist = "The Sword")
        assertEquals("artist:The Sword", query.getQuery())
    }

    @Test
    fun testGetQueryAllTerms() {
        val query = RecordingQuery(artist = "The Sword", title = "Used Future")
        assertEquals("artist:The Sword AND title:Used Future", query.getQuery())
    }
}
