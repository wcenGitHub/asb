package nz.co.asb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.asb.ui.MainActivity
import nz.co.asb.ui.TransactionDetailActivity

@Module
abstract class DaggerUIModule {
    // Contributes activities and fragments
    @ContributesAndroidInjector(modules = [(DaggerModule::class)])
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DaggerModule::class)])
    abstract fun contributesTransactionDetailActivity(): TransactionDetailActivity
}