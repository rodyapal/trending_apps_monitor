import kotlinx.serialization.json.Json
import model.toMarkdown

fun main() {
	val handler = Handler(
		json = Json {
			ignoreUnknownKeys = true
		},
		fileOutputJson = true,
		fileOutputMd = true,
		fileOutputName = "result"
	)
	val requester = Requester(handler)
	requester.requestTrendingApps()

	println(handler.apps.toMarkdown())
}