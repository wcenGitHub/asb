package nz.co.asb.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import nz.co.asb.App
import nz.co.asb.network.RetrofitClient
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Android internal module
        AndroidSupportInjectionModule::class,
        // App level modules
        AppSingletonDaggerModule::class,
        //      UI modules
        DaggerUIModule::class
    ]
)
interface AppSingletonComponent {
    fun inject(app: App)
    fun app(): App
    fun retrofitClient(): RetrofitClient
}
