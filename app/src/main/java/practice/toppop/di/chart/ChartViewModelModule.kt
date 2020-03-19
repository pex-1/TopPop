package practice.toppop.di.chart

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import practice.toppop.di.ViewModelKey
import practice.toppop.ui.chart.ChartViewModel

@Module
abstract class ChartViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChartViewModel::class)
    abstract fun bindChartViewModel(viewModel: ChartViewModel): ViewModel
}