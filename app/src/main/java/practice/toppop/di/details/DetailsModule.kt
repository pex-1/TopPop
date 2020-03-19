package practice.toppop.di.details

import dagger.Module
import dagger.Provides
import practice.toppop.ui.details.DetailsAdapter

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun provideAdapter(): DetailsAdapter = DetailsAdapter()

}