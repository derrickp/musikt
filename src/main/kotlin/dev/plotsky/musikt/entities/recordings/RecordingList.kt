package dev.plotsky.musikt.entities.recordings

import dev.plotsky.musikt.entities.recordings.Recording

data class RecordingList(
    val created: String,
    val count: Int,
    val offset: Int,
    val recordings: List<Recording>
)
