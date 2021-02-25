package nz.co.asb.di

import dagger.Component
import nz.co.asb.viewmodels.MainViewModel
import nz.co.asb.viewmodels.TransactionDetailViewModel

@SessionScope
@Component(
    dependencies = [AppSingletonComponent::class]
)
interface AppSessionComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(transactionDetailViewModel: TransactionDetailViewModel)
}
