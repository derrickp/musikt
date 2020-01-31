package dev.plotsky.musikt.entities.recordings

import com.squareup.moshi.Json
import dev.plotsky.musikt.entities.artists.ArtistCredit
import dev.plotsky.musikt.entities.releases.ReleaseReference

data class Recording(
    val id: String,
    val score: Int?,
    val title: String,
    val length: Int?,
    @Json(name = "artist-credit")
    val artistCredit: List<ArtistCredit> = emptyList(),
    val releases: List<ReleaseReference> = emptyList()

)
