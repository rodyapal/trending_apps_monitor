package model
import kotlinx.serialization.Serializable

@Serializable
data class App(
    val title: String,
    val description: String,
    val developer: String,

    val category: String,
    val content_rating: String,
    val market_url: String,

    val downloads: String,

    val package_name: String,
) {
    fun toMarkdown(): String = "### $title\n" +
            "\t- by $developer with $downloads downloads;\n" +
            "\t- Is $category;\n" +
            "\t- for $content_rating\n" +
            "\t- ${description.replace("\n\n", "\n").replace("-", "\t\t*")}\n" +
            "\t- [Market link]($market_url)"
}

fun List<App>.toMarkdown(): String {
    var markdownString = "## Trending Google Play Market apps\nCreated with 42matters API\n"
    onEach {
        markdownString = "$markdownString- ${it.toMarkdown()}\n"
    }
    return markdownString
}