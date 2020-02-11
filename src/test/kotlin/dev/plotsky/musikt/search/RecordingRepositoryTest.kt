package dev.plotsky.musikt.search

import dev.plotsky.musikt.ReplayTest
import dev.plotsky.musikt.Request
import dev.plotsky.musikt.buildDefaultConfiguration
import kotlin.test.assertEquals
import okreplay.OkReplay
import okreplay.TapeMode
import org.junit.Test

class RecordingRepositoryTest : ReplayTest(TapeMode.READ_ONLY_QUIET) {
    private val request = Request(buildDefaultConfiguration(), client)

    @Test
    @OkReplay
    fun testGetItemsById() {
        val repo = RecordingRepository(request)
        val recording = repo.getById(
            IdOptions("a9a05198-cbb8-4794-b75a-4781485887c0", emptyList())
        )
        assertEquals("a9a05198-cbb8-4794-b75a-4781485887c0", recording?.id)
    }

    @Test
    @OkReplay
    fun testGetItemsWithRelationships() {
        val repo = RecordingRepository(request)
        val options = IdOptions(
            id = "a9a05198-cbb8-4794-b75a-4781485887c0",
            relationships = listOf("releases")
        )
        val recording = repo.getById(options)
        assertEquals("a9a05198-cbb8-4794-b75a-4781485887c0", recording?.id)
        assertEquals(3, recording?.releases?.size)
    }

    @Test
    @OkReplay
    fun testGetByTerm() {
        val repo = RecordingRepository(request)
        val term = "The Sword"
        val recordings = repo.getByTerm(term)
        assertEquals(25, recordings.size)
    }

    @Test
    @OkReplay
    fun testGetByQuery() {
        val repo = RecordingRepository(request)
        val query = RecordingQuery(
            artist = "\"The Sword\"",
            title = "Used Future"
        )
        val recordings = repo.getByQuery(query)
        assertEquals(13, recordings.size)
        assertEquals(100, recordings[0].score)
    }

    @Test
    @OkReplay
    fun testGetByQueryWithReplacements() {
        val repo = RecordingRepository(request)

        val query = RecordingQuery(
            artist = "\"Zeal & Ardor\"",
            title = "Gravedigger's Chant"
        )
        val recordings = repo.getByQuery(query)
        assertEquals(2, recordings.size)
    }

    @Test
    @OkReplay
    fun testGetByQueryWithArtistName() {
        val query = RecordingQuery(
            artistname = "\"Uncle Acid & The Deadbeats\"",
            title = "Slow Death"
        )
        val repo = RecordingRepository(request)
        val recordings = repo.getByQuery(query)
        assertEquals(4, recordings.size)
    }

    @Test
    @OkReplay
    fun testGetAKnownArtist() {
        val query = RecordingQuery(
            artistname = "\"Lord Vapour\"",
            title = "Burning Planet"
        )
        val repo = RecordingRepository(request)
        val recordings = repo.getByQuery(query)
        assertEquals(1, recordings.size)
    }
}
