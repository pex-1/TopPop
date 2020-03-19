package practice.toppop.data.model.album


import com.google.gson.annotations.SerializedName

data class Track(
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
    @SerializedName("preview")
    val preview: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("type")
    val type: String
){
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Track

        if (duration != other.duration) return false
        if (explicitContentCover != other.explicitContentCover) return false
        if (explicitContentLyrics != other.explicitContentLyrics) return false
        if (explicitLyrics != other.explicitLyrics) return false
        if (id != other.id) return false
        if (link != other.link) return false
        if (preview != other.preview) return false
        if (rank != other.rank) return false
        if (readable != other.readable) return false
        if (title != other.title) return false
        if (titleShort != other.titleShort) return false
        if (titleVersion != other.titleVersion) return false
        if (type != other.type) return false

        return true
    }
}