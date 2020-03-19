package practice.toppop.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import practice.toppop.di.chart.ChartModule
import practice.toppop.di.chart.ChartScope
import practice.toppop.di.chart.ChartViewModelModule
import practice.toppop.di.details.DetailsModule
import practice.toppop.di.details.DetailsScope
import practice.toppop.di.details.DetailsViewModelModule
import practice.toppop.ui.chart.ChartActivity
import practice.toppop.ui.details.DetailsActivity

@Module
abstract class ActivityBuildersModule {

    @ChartScope
    @ContributesAndroidInjector(modules = [ChartViewModelModule::class, ChartModule::class])
    abstract fun contributeChartActivity(): ChartActivity

    @DetailsScope
    @ContributesAndroidInjector(modules = [DetailsViewModelModule::class, DetailsModule::class])
    abstract fun contributeDetailsActivity(): DetailsActivity
}