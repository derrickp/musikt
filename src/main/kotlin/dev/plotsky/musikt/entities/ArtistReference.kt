package dev.plotsky.musikt.entities

import com.squareup.moshi.Json

data class ArtistReference(
    val id: String,
    val name: String,
    @Json(name = "sort-name") val sortName: String,
    val disambiguation: String
)
