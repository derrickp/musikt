package dev.plotsky.musikt.entities

import com.squareup.moshi.Json

data class AreaReference(
    val id: String,
    val name: String,
    @Json(name = "sort-name") val sortName: String,
    @Json(name = "iso-3166-1-codes") val iso31661codes: List<String>
)
