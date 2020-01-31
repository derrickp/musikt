package dev.plotsky.musikt.entities.releases

import com.squareup.moshi.Json

data class ReleaseReference(
    val id: String,
    val count: Int?,
    val title: String,
    val status: String?,
    @Json(name = "release-group") val releaseGroup: ReleaseGroup?,
    val date: String?,
    val country: String?,
    @Json(name = "release-events")
    val releaseEvents: List<ReleaseEvent> = emptyList(),
    @Json(name = "track-count") val trackCount: Int?,
    val media: List<Media> = emptyList()
)
