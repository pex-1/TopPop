package practice.toppop.data.model.chart


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("picture_big")
    val pictureBig: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_xl")
    val pictureXl: String,
    @SerializedName("radio")
    val radio: Boolean,
    @SerializedName("tracklist")
    val tracklist: String,
    @SerializedName("type")
    val type: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Artist

        if (id != other.id) return false
        if (link != other.link) return false
        if (name != other.name) return false
        if (picture != other.picture) return false
        if (pictureBig != other.pictureBig) return false
        if (pictureMedium != other.pictureMedium) return false
        if (pictureSmall != other.pictureSmall) return false
        if (pictureXl != other.pictureXl) return false
        if (radio != other.radio) return false
        if (tracklist != other.tracklist) return false
        if (type != other.type) return false

        return true
    }
}