package dev.plotsky.musikt.entities

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.plotsky.musikt.entities.recordings.RecordingList
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class RecordingListTest {
    @Test
    fun testSomeLibraryMethod() {
        val json = File("src/test/data/recording_list.json")
            .readText()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(RecordingList::class.java)
        val recordingList = adapter.fromJson(json)
        assertEquals(13, recordingList?.recordings?.size)
    }
}
