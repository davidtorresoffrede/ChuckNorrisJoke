package d.offrede.categories.data.service

import retrofit2.Response
import retrofit2.http.GET

interface CategoriesApi {
    @GET("jokes/categories")
    suspend fun getCategories(): Response<List<String>>
}