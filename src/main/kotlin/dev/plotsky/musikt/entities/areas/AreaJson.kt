package dev.plotsky.musikt.entities.areas

import com.squareup.moshi.Json
import dev.plotsky.musikt.entities.LifeSpan

data class AreaJson(
    val id: String,
    val relations: List<Map<String, Any>> = emptyList(),
    val disambiguation: String = "",
    @Json(name = "sort-name")
    val sortName: String = "",
    val name: String,
    @Json(name = "type-id")
    val typeId: String = "",
    @Json(name = "life-span")
    val lifeSpan: LifeSpan?,
    val score: Int?,
    val type: String? = ""
)
