package dev.plotsky.musikt.search

import dev.plotsky.musikt.Request
import dev.plotsky.musikt.entities.Recording
import dev.plotsky.musikt.entities.RecordingList

class RecordingRepository(
    private val request: Request
) : Repository<Recording> {
    private val idSearch = MusicbrainzIdSearch(Recording::class.java, request)
    private val listSearch = MusicbrainzListSearch(
        RecordingList::class.java,
        request
    )

    private val endpoint = "recording"

    override fun getById(idOptions: IdOptions): Recording? {
        return idSearch.getItemById(endpoint, idOptions)
    }

    override fun getByTerm(term: String): List<Recording> {
        val results = listSearch.byTerm(endpoint, term)
        return results?.recordings ?: emptyList()
    }

    override fun getByQuery(query: Query): List<Recording> {
        val results = listSearch.byQuery(endpoint, query)
        return results?.recordings ?: emptyList()
    }
}
