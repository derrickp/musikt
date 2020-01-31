package dev.plotsky.musikt.entities.areas

data class AreaList(
    val created: String,
    val count: Int,
    val offset: Int,
    val areas: List<Area> = emptyList()
)
