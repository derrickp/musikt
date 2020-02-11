package dev.plotsky.musikt.entities.areas

import com.squareup.moshi.Json
import dev.plotsky.musikt.entities.LifeSpan

data class Area(
    val id: String,
    val relatedAreas: List<RelatedArea> = emptyList(),
    val disambiguation: String = "",
    val score: Int?,
    @Json(name = "sort-name")
    val sortName: String = "",
    val name: String,
    @Json(name = "type-id")
    val typeId: String = "",
    @Json(name = "life-span")
    val lifeSpan: LifeSpan?,
    val type: String? = ""
)
