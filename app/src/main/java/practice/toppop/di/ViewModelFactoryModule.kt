package practice.toppop.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import practice.toppop.ui.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?
}