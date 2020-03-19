package practice.toppop.data

import io.reactivex.Flowable
import practice.toppop.api.DeezerApi
import practice.toppop.data.model.album.AlbumResponse
import practice.toppop.data.model.chart.ChartResponse
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: DeezerApi) {

    fun getChart():Flowable<ChartResponse>{
        return apiService.getChart()
    }

    fun getAlbum(id: Int): Flowable<AlbumResponse>{
        return apiService.getAlbum(id)
    }

}