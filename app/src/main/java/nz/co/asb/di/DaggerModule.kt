package nz.co.asb.di

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import nz.co.asb.App
import nz.co.asb.ui.MainActivity
import nz.co.asb.ui.TransactionDetailActivity
import nz.co.asb.viewmodels.MainViewModel
import nz.co.asb.viewmodels.TransactionDetailViewModel
import nz.co.asb.viewmodels.ViewModelFactory

@Module
class DaggerModule {

    // provide view models
    @Provides
    fun provideMainViewModel(activity: MainActivity, app: App): MainViewModel {
        return ViewModelProvider(activity, ViewModelFactory(app))
            .get(MainViewModel::class.java)
    }

    @Provides
    fun provideTransactionDetailViewModel(activity: TransactionDetailActivity, app: App): TransactionDetailViewModel {
        return ViewModelProvider(activity, ViewModelFactory(app))
            .get(TransactionDetailViewModel::class.java)
    }
}
