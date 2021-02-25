package nz.co.asb

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import nz.co.asb.di.*
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    companion object {
        lateinit var appSingletonComponent: AppSingletonComponent
        lateinit var appSessionComponent: AppSessionComponent
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        appSingletonComponent = DaggerAppSingletonComponent.builder()
            .appSingletonDaggerModule(AppSingletonDaggerModule(this))
            .build()
        appSingletonComponent.inject(this)

        appSessionComponent = DaggerAppSessionComponent.builder()
            .appSingletonComponent(appSingletonComponent).build()

    }
}