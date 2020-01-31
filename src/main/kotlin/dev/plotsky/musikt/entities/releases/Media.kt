package dev.plotsky.musikt.entities.releases

import com.squareup.moshi.Json

data class Media(
    val position: Int,
    val format: String?,
    val track: List<Track>,
    @Json(name = "track-count") val trackCount: Int,
    @Json(name = "track-offset") val trackOffset: Int
)
