package practice.toppop.data.model.chart


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Album

        if (cover != other.cover) return false
        if (coverBig != other.coverBig) return false
        if (coverMedium != other.coverMedium) return false
        if (coverSmall != other.coverSmall) return false
        if (coverXl != other.coverXl) return false
        if (id != other.id) return false
        if (title != other.title) return false
        if (tracklist != other.tracklist) return false
        if (type != other.type) return false

        return true

    }
}