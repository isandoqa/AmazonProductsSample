package sample.ishaq.amazon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sample.ishaq.amazon.api.ApiHelper
import sample.ishaq.amazon.dataSource.main.DefaultMainRepo
import sample.ishaq.amazon.dataSource.main.MainRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    @Singleton
    fun provideMainRepo(
        apiHelper: ApiHelper
    ): MainRepo {
        return DefaultMainRepo(apiHelper)
    }
}