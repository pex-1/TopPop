package practice.toppop.data.model.album


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("data")
    val data: List<Track>
)