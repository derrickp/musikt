package dev.plotsky.musikt.search.areas

import dev.plotsky.musikt.Request
import dev.plotsky.musikt.entities.areas.Area
import dev.plotsky.musikt.entities.areas.AreaList
import dev.plotsky.musikt.entities.areas.LocalAreaAdapter
import dev.plotsky.musikt.search.IdOptions
import dev.plotsky.musikt.search.MusicbrainzIdSearch
import dev.plotsky.musikt.search.MusicbrainzListSearch
import dev.plotsky.musikt.search.Query
import dev.plotsky.musikt.search.Repository

class AreaRepository(request: Request) : Repository<Area> {
    companion object MoshiCreator {
        fun getAdapter(): LocalAreaAdapter {
            return LocalAreaAdapter()
        }
    }
    private val idSearch = MusicbrainzIdSearch(
        Area::class.java,
        request,
        listOf(getAdapter())
    )
    private val listSearch = MusicbrainzListSearch(
        AreaList::class.java,
        request,
        listOf(getAdapter())
    )
    private val endpoint = "area"

    override fun getById(idOptions: IdOptions): Area? {
        return idSearch.getItemById(endpoint, idOptions)
    }

    override fun getByTerm(term: String): List<Area> {
        val results = listSearch.byTerm(endpoint, term)
        return results?.areas ?: emptyList()
    }

    override fun getByQuery(query: Query): List<Area> {
        val results = listSearch.byQuery(endpoint, query)
        return results?.areas ?: emptyList()
    }
}
