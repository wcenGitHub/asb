package nz.co.asb.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nz.co.asb.ui.MainActivity

@Module
abstract class DaggerUIModule {
    // Contributes activities and fragments
    @ContributesAndroidInjector(modules = [(DaggerModule::class)])
    abstract fun contributesMainActivity(): MainActivity
}