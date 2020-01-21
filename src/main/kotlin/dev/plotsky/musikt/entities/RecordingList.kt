package dev.plotsky.musikt.entities

data class RecordingList(
    val created: String,
    val count: Int,
    val offset: Int,
    val recordings: List<Recording>
)
