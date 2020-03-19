package practice.toppop.di.chart

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import practice.toppop.ui.chart.ChartActivity
import practice.toppop.ui.chart.ChartAdapter
import practice.toppop.util.Constants
import practice.toppop.util.VerticalSpacingItemDecoration

@Module
class ChartModule {

    @ChartScope
    @Provides
    fun provideAdapter(clickListener: ChartAdapter.OnArtistClicked, picasso: Picasso): ChartAdapter = ChartAdapter(clickListener, picasso)

    @ChartScope
    @Provides
    fun provideVerticalSpacing(): VerticalSpacingItemDecoration = VerticalSpacingItemDecoration(Constants.VERTICAL_SPACING)

    @ChartScope
    @Provides
    fun provideOnClickListener(chartActivity: ChartActivity):ChartAdapter.OnArtistClicked{
        return chartActivity
    }
}