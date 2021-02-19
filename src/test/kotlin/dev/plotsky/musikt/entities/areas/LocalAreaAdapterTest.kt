package dev.plotsky.musikt.entities.areas

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class LocalAreaAdapterTest {
    @Test
    fun testFromJson() {
        val moshi = Moshi.Builder()
            .add(LocalAreaAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<Area> = moshi.adapter(Area::class.java)
        val json = File("src/test/data/area_with_relations.json")
            .readText()
        val area = adapter.fromJson(json)!!
        assertEquals(1, area.relatedAreas.size)
    }
}
