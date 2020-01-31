package dev.plotsky.musikt.entities.artists

import com.squareup.moshi.Json
import dev.plotsky.musikt.entities.LifeSpan
import dev.plotsky.musikt.entities.areas.AreaReference
import dev.plotsky.musikt.entities.recordings.RecordingReference
import dev.plotsky.musikt.entities.releases.ReleaseReference

data class Artist(
    val id: String,
    val score: Int?,
    @Json(name = "life-span")
    val lifeSpan: LifeSpan?,
    val country: String?,
    val name: String,
    @Json(name = "type-id")
    val typeId: String,
    val gender: String?,
    @Json(name = "end_area")
    val endArea: AreaReference?,
    val isnis: List<String> = emptyList(),
    val type: String?,
    @Json(name = "begin_area")
    val beginArea: AreaReference?,
    val disambiguation: String?,
    @Json(name = "sort-name")
    val sortName: String?,
    @Json(name = "gender-id")
    val genderId: String?,
    val area: AreaReference?,
    val releases: List<ReleaseReference> = emptyList(),
    val recordings: List<RecordingReference> = emptyList()
)
