package practice.toppop.di.details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import practice.toppop.di.ViewModelKey
import practice.toppop.ui.details.DetailsViewModel

@Module
abstract class DetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModule(viewModel: DetailsViewModel): ViewModel
}