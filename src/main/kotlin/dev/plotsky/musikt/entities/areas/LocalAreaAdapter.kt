package dev.plotsky.musikt.entities.areas

import com.squareup.moshi.Moshi
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class LocalAreaAdapter {
    @ToJson
    fun toJson(area: Area): AreaJson {
        val areaRelations: List<Map<String, Any>> =  area.relatedAreas.map {
            mapOf(
                "type" to it.type,
                "target-type" to it.targetType,
                "direction" to it.direction,
                "type-id" to it.typeId,
                "area" to mapOf(
                    "disambiguation" to it.area.disambiguation,
                    "sort-name" to it.area.sortName,
                    "name" to it.area.name,
                    "id" to it.area.id
                )
            )
        }
        return AreaJson(
            id = area.id,
            name = area.name,
            sortName = area.sortName,
            disambiguation = area.disambiguation,
            lifeSpan = area.lifeSpan,
            relations = areaRelations
        )
    }

    @FromJson
    fun fromJson(areaJson: AreaJson): Area {
        val relatedAreas = areaJson.relations
            .filter { it["target-type"] == "area" }
            .mapNotNull { buildRelatedArea(it) }
        return Area(
            id = areaJson.id,
            disambiguation = areaJson.disambiguation,
            name = areaJson.name,
            sortName = areaJson.sortName,
            typeId = areaJson.typeId,
            lifeSpan = areaJson.lifeSpan,
            relatedAreas = relatedAreas
        )
    }

    private fun buildRelatedArea(json: Map<String, Any>): RelatedArea? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val mapAdapter = moshi.adapter(Map::class.java)
        val serialized = mapAdapter.toJson(json)
        val relatedAreaAdapter = moshi.adapter(RelatedArea::class.java)
        return relatedAreaAdapter.fromJson(serialized)
    }
}
