package dev.plotsky.musikt.search

import dev.plotsky.musikt.ReplayTest
import dev.plotsky.musikt.Request
import dev.plotsky.musikt.buildDefaultConfiguration
import dev.plotsky.musikt.entities.Recording
import kotlin.test.assertEquals
import okreplay.OkReplay
import okreplay.TapeMode
import org.junit.Test

class RecordingRepositoryTest : ReplayTest(TapeMode.READ_ONLY_QUIET) {
    private val request = Request(buildDefaultConfiguration(), client)

    @Test
    @OkReplay
    fun testGetItemsById() {
        val repo = MusicbrainzEntityRepository(Recording::class.java, request)
        val recording = repo.getItemById(
            "recording",
            IdOptions("a9a05198-cbb8-4794-b75a-4781485887c0", emptyList()))
        assertEquals("a9a05198-cbb8-4794-b75a-4781485887c0", recording?.id)
    }

    @Test
    @OkReplay
    fun testGetItemsWithRelationships() {
        val repo = MusicbrainzEntityRepository(Recording::class.java, request)
        val options = IdOptions(
            id = "a9a05198-cbb8-4794-b75a-4781485887c0",
            relationships = listOf("releases")
        )
        val recording = repo.getItemById("recording", options)
        assertEquals("a9a05198-cbb8-4794-b75a-4781485887c0", recording?.id)
        assertEquals(3, recording?.releases?.size)
    }
}
