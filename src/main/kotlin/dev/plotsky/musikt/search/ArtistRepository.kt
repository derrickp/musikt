package dev.plotsky.musikt.search

import dev.plotsky.musikt.Request
import dev.plotsky.musikt.entities.artists.Artist
import dev.plotsky.musikt.entities.artists.ArtistList

class ArtistRepository(request: Request) : Repository<Artist> {
    private val idSearch = MusicbrainzIdSearch(Artist::class.java, request)
    private val listSearch = MusicbrainzListSearch(
        ArtistList::class.java,
        request
    )
    private val endpoint = "artist"

    override fun getById(idOptions: IdOptions): Artist? {
        return idSearch.getItemById(endpoint, idOptions)
    }

    override fun getByTerm(term: String): List<Artist> {
        val results = listSearch.byTerm(endpoint, term)
        return results?.artists ?: emptyList()
    }

    override fun getByQuery(query: Query): List<Artist> {
        val results = listSearch.byQuery(endpoint, query)
        return results?.artists ?: emptyList()
    }
}
