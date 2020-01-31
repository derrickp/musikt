package dev.plotsky.musikt.entities.recordings

data class RecordingReference(
    val title: String,
    val id: String,
    val video: Boolean?,
    val disambiguation: String = "",
    val length: Int?
)
