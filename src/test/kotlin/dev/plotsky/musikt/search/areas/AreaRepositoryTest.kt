package dev.plotsky.musikt.search.areas

import dev.plotsky.musikt.ReplayTest
import dev.plotsky.musikt.Request
import dev.plotsky.musikt.buildDefaultConfiguration
import dev.plotsky.musikt.search.IdOptions
import kotlin.test.assertEquals
import okreplay.OkReplay
import okreplay.TapeMode
import org.junit.Test

class AreaRepositoryTest : ReplayTest(TapeMode.READ_ONLY_QUIET) {
    private val request = Request(buildDefaultConfiguration(), client)

    @Test
    @OkReplay
    fun testGetItemsById() {
        val repo = AreaRepository(request)
        val area = repo.getById(
            IdOptions("2bda1647-f64b-4277-acce-e9c1296d065e", emptyList())
        )!!
        assertEquals("2bda1647-f64b-4277-acce-e9c1296d065e", area.id)
    }

    @Test
    @OkReplay
    fun testGetByIdReturnsType() {
        val repo = AreaRepository(request)
        val area = repo.getById(
            IdOptions("b21e4552-050d-4c0c-ac5a-031108eb0c47", listOf("area-rels"))
        )!!
        assertEquals("City", area.type)
    }

    @Test
    @OkReplay
    fun testGetItemsWithRelationships() {
        val repo = AreaRepository(request)
        val options = IdOptions(
            id = "2bda1647-f64b-4277-acce-e9c1296d065e",
            relationships = listOf("area-rels")
        )
        val area = repo.getById(options)!!
        assertEquals("2bda1647-f64b-4277-acce-e9c1296d065e", area.id)
        assertEquals(1, area.relatedAreas.size)
    }

    @Test
    @OkReplay
    fun testGetByTerm() {
        val repo = AreaRepository(request)
        val term = "Calgary"
        val areas = repo.getByTerm(term)
        assertEquals(1, areas.size)
    }

    @Test
    @OkReplay
    fun testGetByQuery() {
        val repo = AreaRepository(request)
        val query = AreaQuery(
            area = "Calgary"
        )
        val areas = repo.getByQuery(query)
        assertEquals(1, areas.size)
        assertEquals(100, areas[0].score)
    }
}
