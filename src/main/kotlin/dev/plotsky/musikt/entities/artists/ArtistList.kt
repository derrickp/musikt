package dev.plotsky.musikt.entities.artists

data class ArtistList(
    val created: String,
    val count: Int,
    val offset: Int,
    val artists: List<Artist> = emptyList()
)
