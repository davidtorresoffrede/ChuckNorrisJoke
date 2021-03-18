package d.offrede.core.domain.model

data class JokeDomain(
    val categories: List<String>? = null,
    val iconUrl: String? = null,
    val url: String? = null,
    val text: String? = null
)