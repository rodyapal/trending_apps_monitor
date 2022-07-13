package model
import kotlinx.serialization.Serializable

@Serializable
data class TrendingApiResponse(
    val app_list: List<App>,
    val cat_key: String,
    val date: String,
    val limit: Int,
    val list_name: String,
)