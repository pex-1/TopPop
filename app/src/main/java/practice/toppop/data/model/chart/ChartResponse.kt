package practice.toppop.data.model.chart


import com.google.gson.annotations.SerializedName
import practice.toppop.data.model.chart.ChartData

data class ChartResponse(
    @SerializedName("data")
    val data: List<ChartData>,
    @SerializedName("total")
    val total: Int
)