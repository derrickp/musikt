package dev.plotsky.musikt.entities.artists

import dev.plotsky.musikt.entities.artists.Artist

data class ArtistList(
    val created: String,
    val count: Int,
    val offset: Int,
    val artists: List<Artist> = emptyList()
)
