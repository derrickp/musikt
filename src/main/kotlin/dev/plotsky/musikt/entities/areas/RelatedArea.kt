package dev.plotsky.musikt.entities.areas

import com.squareup.moshi.Json

data class RelatedArea(
    val area: AreaReference,
    val direction: String,
    val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "target-type") val targetType: String = ""
)
