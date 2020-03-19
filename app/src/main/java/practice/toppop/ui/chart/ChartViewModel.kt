package practice.toppop.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import practice.toppop.data.Repository
import practice.toppop.data.model.NetworkResponse
import practice.toppop.data.model.chart.ChartData
import practice.toppop.data.model.chart.ChartResponse
import javax.inject.Inject

class ChartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private lateinit var disposable: Disposable

    private val _chart: MutableLiveData<List<ChartData>> = MutableLiveData()
    val chart: LiveData<List<ChartData>>
        get() = _chart

    private val _errorHandling: MutableLiveData<NetworkResponse> = MutableLiveData()
    val errorHandling: LiveData<NetworkResponse>
        get() = _errorHandling

    init {
        getChart()
    }

    fun getChart() {
        disposable = repository.getChart()
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

    private fun onRetrieveListSuccess(it: ChartResponse) {
        _chart.value = it.data
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