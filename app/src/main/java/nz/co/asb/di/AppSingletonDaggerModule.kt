package nz.co.asb.di

import dagger.Module
import dagger.Provides
import nz.co.asb.App
import javax.inject.Singleton

@Module
class AppSingletonDaggerModule(val app: App) {

    @Singleton
    @Provides
    fun provideApplication() = app

}
