package d.offrede.categories.domain.repository

import java.lang.Exception

interface CategoriesRepository {
    @Throws(Exception::class)
    suspend fun getCategories() : List<String>
}