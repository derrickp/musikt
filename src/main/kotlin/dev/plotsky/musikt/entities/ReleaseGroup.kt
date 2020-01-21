package dev.plotsky.musikt.entities

import com.squareup.moshi.Json

data class ReleaseGroup(
    val id: String,
    @Json(name = "type-id") val typeId: String,
    val title: String,
    @Json(name = "primary-type") val primaryType: String
)
