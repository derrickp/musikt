package dev.plotsky.musikt.entities

import com.squareup.moshi.Json

data class Recording(
    val id: String,
    val score: Int?,
    val title: String,
    val length: Int?,
    @Json(name = "artist-credit")
    val artistCredit: List<ArtistCredit> = emptyList(),
    val releases: List<ReleaseReference> = emptyList()

)
