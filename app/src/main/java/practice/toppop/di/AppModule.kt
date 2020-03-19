package practice.toppop.di

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import practice.toppop.api.DeezerApi
import practice.toppop.data.Repository
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providePicasso(): Picasso = Picasso.get()

    @Singleton
    @Provides
    fun provideRepository(apiService: DeezerApi): Repository = Repository(apiService)
}