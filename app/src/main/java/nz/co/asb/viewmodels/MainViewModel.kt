package nz.co.asb.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import nz.co.asb.App
import nz.co.asb.model.Repository
import nz.co.asb.model.Transaction
import java.lang.Exception
import javax.inject.Inject

class MainViewModel(app: App) : BaseViewModel(app) {

    @Inject
    lateinit var repository: Repository

    val transactionListLiveData: MutableLiveData<List<Transaction>> get() {
        return repository.transactionListLiveData
    }

    init {
        App.appSessionComponent.inject(this)
    }

    suspend fun fetchTransactions() {
        try {
            repository.fetchTransactions()
        } catch (e: Exception) {
            e.printStackTrace()
            errorData.value = e
        }
    }

    fun orderTransactionList(transactionList: List<Transaction>): List<Transaction> {
        return transactionList.sortedByDescending { transaction -> transaction.transactionDate }
    }
}