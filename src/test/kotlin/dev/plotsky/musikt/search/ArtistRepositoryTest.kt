package dev.plotsky.musikt.search

import dev.plotsky.musikt.ReplayTest
import dev.plotsky.musikt.Request
import dev.plotsky.musikt.buildDefaultConfiguration
import kotlin.test.assertEquals
import okreplay.OkReplay
import okreplay.TapeMode
import org.junit.Test

class ArtistRepositoryTest : ReplayTest(TapeMode.READ_WRITE) {
    private val request = Request(buildDefaultConfiguration(), client)

    @Test
    @OkReplay
    fun testGetItemsById() {
        val repo = ArtistRepository(request)
        val artist = repo.getById(
            IdOptions("a466c2a2-6517-42fb-a160-1087c3bafd9f", emptyList())
        )
        assertEquals("a466c2a2-6517-42fb-a160-1087c3bafd9f", artist?.id)
    }

    @Test
    @OkReplay
    fun testGetItemsWithRelationships() {
        val repo = ArtistRepository(request)
        val options = IdOptions(
            id = "a466c2a2-6517-42fb-a160-1087c3bafd9f",
            relationships = listOf("releases")
        )
        val artist = repo.getById(options)
        assertEquals("a466c2a2-6517-42fb-a160-1087c3bafd9f", artist?.id)
        assertEquals(25, artist?.releases?.size)
    }

    @Test
    @OkReplay
    fun testGetByTerm() {
        val repo = ArtistRepository(request)
        val term = "Slipknot"
        val artists = repo.getByTerm(term)
        assertEquals(9, artists.size)
    }

    @Test
    @OkReplay
    fun testGetByQuery() {
        val repo = ArtistRepository(request)
        val query = ArtistQuery(
            artist = "\"Slipknot\""
        )
        val artists = repo.getByQuery(query)
        assertEquals(4, artists.size)
        assertEquals(100, artists[0].score)
    }

    @Test
    @OkReplay
    fun testGetByQueryWithReplacements() {
        val repo = ArtistRepository(request)

        val query = ArtistQuery(
            artist = "\"Zeal & Ardor\""
        )
        val artists = repo.getByQuery(query)
        assertEquals(1, artists.size)
    }

    @Test
    @OkReplay
    fun testGetByQueryWithArtistName() {
        val query = ArtistQuery(
            artist = "\"Uncle Acid & The Deadbeats\""
        )
        val repo = ArtistRepository(request)
        val artists = repo.getByQuery(query)
        assertEquals(1, artists.size)
    }
}
