package dev.plotsky.musikt.entities.releases

import dev.plotsky.musikt.entities.areas.AreaReference

data class ReleaseEvent(
    val date: String,
    val area: AreaReference
)
