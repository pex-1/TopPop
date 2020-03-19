package practice.toppop.api

import io.reactivex.Flowable
import practice.toppop.data.model.album.AlbumResponse
import practice.toppop.data.model.chart.ChartResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {

    @GET("chart/0/tracks")
    fun getChart(): Flowable<ChartResponse>

    @GET("album/{album_id}")
    fun getAlbum(@Path("album_id") albumId: Int): Flowable<AlbumResponse>

}