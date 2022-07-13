import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import model.*

class Requester(
	private val responseHandler: Handler
) {

	init {
		FuelManager.instance.basePath = API_URL
		FuelManager.instance.baseParams = listOf("access_token" to API_KEY)
	}

	fun requestTrendingApps() {
		val trendingAppsAsyncRequest = "/$REQUEST_TOP_CHARTS"
			.httpGet(
				listOf(
					PARAM_NAME_LIST to PARAM_VAL_LIST_TRENDING,
					PARAM_NAME_LIMIT to PARAM_VAL_LIMIT
				)
			)
			.responseString { _, _, result ->
				when (result) {
					is Result.Failure -> {
						val ex = result.getException()
						println(ex)
					}
					is Result.Success -> {
						val data = result.get()
						responseHandler.processTrendingApps(data)
					}
				}
			}
		trendingAppsAsyncRequest.join()
	}
}