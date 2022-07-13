import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import model.App
import model.TrendingApiResponse
import model.toMarkdown
import java.io.File

class Handler(
	private val json: Json,
	private val fileOutputJson: Boolean = false,
	private val fileOutputMd: Boolean = false,
	private val fileOutputName: String = ""
) {

	private var _apps = mutableListOf<App>()
	val apps get() = _apps as List<App>

	fun processTrendingApps(data: String) {
		val elements = json.decodeFromJsonElement<TrendingApiResponse>(
			json.parseToJsonElement(data)
		)
		_apps = elements.app_list as MutableList<App>

		if (fileOutputJson || fileOutputMd) {
			writeToFiles()
		}
	}

	private fun writeToFiles() {
		if (fileOutputJson) {
			File("$fileOutputName.json").printWriter().use { out ->
				out.println(json.encodeToString(apps))
			}
		}

		if (fileOutputMd) {
			File("$fileOutputName.md").printWriter().use { out ->
				out.println(apps.toMarkdown())
			}
		}
	}
}