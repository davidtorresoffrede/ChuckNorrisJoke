package d.offrede.random_joke.presentation.model

data class Joke(
    val categories: List<String> = listOf(),
    val iconUrl: String = "",
    val url: String = "",
    val text: String = ""
)