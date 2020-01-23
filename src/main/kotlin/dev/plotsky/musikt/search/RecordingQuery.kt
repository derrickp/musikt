package dev.plotsky.musikt.search

class RecordingQuery(val artist: String?, val title: String? = null) : Query {
    override fun getQuery(): String {
        val terms = mapOf("artist" to artist, "title" to title)
            .filterValues { it != null }
        return terms.map { "${it.key}:${it.value}" }.joinToString(" AND ")
    }
}
