package d.offrede.search.data.service

import d.offrede.search.data.model.SearchResultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("jokes/search")
    suspend fun search(@Query("query") query: String): Response<SearchResultData>
}