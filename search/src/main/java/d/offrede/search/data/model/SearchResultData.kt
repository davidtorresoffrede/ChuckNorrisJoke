package d.offrede.search.data.model

import com.google.gson.annotations.SerializedName
import d.offrede.core.data.model.JokeData

data class SearchResultData(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("result") val jokes: List<JokeData?>? = null
)
