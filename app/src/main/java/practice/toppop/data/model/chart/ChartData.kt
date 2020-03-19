package practice.toppop.data.model.chart


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChartData(
    @SerializedName("album")
    val album: Album,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("type")
    val type: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as ChartData

        if (album != other.album) return false
        if (artist != other.artist) return false
        if (duration != other.duration) return false
        if (explicitContentCover != other.explicitContentCover) return false
        if (explicitContentLyrics != other.explicitContentLyrics) return false
        if (explicitLyrics != other.explicitLyrics) return false
        if (id != other.id) return false
        if (position != other.position) return false
        if (preview != other.preview) return false
        if (rank != other.rank) return false
        if (title != other.title) return false
        if (titleShort != other.titleShort) return false
        if (titleVersion != other.titleVersion) return false
        if (type != other.type) return false
        if (album != other.album) return false
        if (album != other.album) return false

        return true
    }
}