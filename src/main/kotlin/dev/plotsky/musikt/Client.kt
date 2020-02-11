package dev.plotsky.musikt

import dev.plotsky.musikt.search.areas.AreaRepository
import dev.plotsky.musikt.search.artists.ArtistRepository
import dev.plotsky.musikt.search.recordings.RecordingRepository
import okhttp3.OkHttpClient

class Client internal constructor (request: Request) {
    private val artistRepository = ArtistRepository(request)
    private val areaRepository = AreaRepository(request)
    private val recordingRepository = RecordingRepository(request)

    val artists: ArtistRepository
        get() = artistRepository
    val recordings: RecordingRepository
        get() = recordingRepository
    val areas: AreaRepository
        get() = areaRepository

    companion object Factory {
        fun build(config: Configuration): Client {
            val client = OkHttpClient().newBuilder().build()
            val request = Request(config, client)
            return Client(request)
        }
    }
}
