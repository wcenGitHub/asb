package nz.co.asb.viewmodels

import nz.co.asb.App
import nz.co.asb.model.Repository
import nz.co.asb.model.Transaction
import javax.inject.Inject

class TransactionDetailViewModel(app: App) : BaseViewModel(app) {

    @Inject
    lateinit var repository: Repository

    init {
        App.appSessionComponent.inject(this)
    }

    fun getTransactionById(transactionId: String): Transaction? {
        return repository.getTransactionById(transactionId)
    }
}