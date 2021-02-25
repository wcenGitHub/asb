package nz.co.asb.di

import dagger.Component
import nz.co.asb.viewmodels.MainViewModel

@SessionScope
@Component(
    dependencies = [AppSingletonComponent::class]
)
interface AppSessionComponent {

    fun inject(mainViewModel: MainViewModel)
}
