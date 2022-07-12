import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result


fun main() {
	FuelManager.instance.basePath = API_URL
	FuelManager.instance.baseParams = listOf("access_token" to API_KEY)
	val asyncRequest = "/$REQUEST_TOP_CHARTS"
		.httpGet(
			listOf(
				PARAM_NAME_LIST to PARAM_NAME_LIST_TRENDING,
				PARAM_NAME_LIMIT to 5
			)
		)
		.responseString { request, response, result ->
			when (result) {
				is Result.Failure -> {
					val ex = result.getException()
					println(ex)
				}
				is Result.Success -> {
					val data = result.get()
					println(data)
				}
			}
		}
	asyncRequest.join()
}