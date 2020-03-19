package practice.toppop.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import practice.toppop.data.Repository
import practice.toppop.data.model.NetworkResponse
import practice.toppop.data.model.album.AlbumResponse
import practice.toppop.data.model.album.Tracks
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private lateinit var disposable: Disposable

    private val _album: MutableLiveData<Tracks> = MutableLiveData()
    val album: LiveData<Tracks>
        get() = _album

    private val _errorHandling: MutableLiveData<NetworkResponse> = MutableLiveData()
    val errorHandling: LiveData<NetworkResponse>
        get() = _errorHandling


    fun getAlbum(id: Int) {
        disposable = repository.getAlbum(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveListLoading() }
            .subscribe(
                {
                    onRetrieveListSuccess(it)
                },
                {
                    onRetrieveListError(it)
                }
            )
    }

    private fun onRetrieveListSuccess(it: AlbumResponse) {
        _album.value = it.tracks
        _errorHandling.value = NetworkResponse.success()
    }

    private fun onRetrieveListLoading() {
        _errorHandling.value = NetworkResponse.loading()
    }

    private fun onRetrieveListError(it: Throwable) {
        _errorHandling.value = NetworkResponse.error(it.localizedMessage)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}