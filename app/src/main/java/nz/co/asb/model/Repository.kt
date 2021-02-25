package nz.co.asb.model

import androidx.lifecycle.MutableLiveData
import nz.co.asb.di.SessionScope
import javax.inject.Inject

@SessionScope
class Repository @Inject constructor() {

    @Inject
    lateinit var dataSource: DataSource

    var transactionList: MutableList<Transaction> = mutableListOf()

    val transactionListLiveData = MutableLiveData<List<Transaction>>()

    suspend fun fetchTransactions() {
        transactionList = dataSource.fetchTransactions().toMutableList()
        transactionListLiveData.value = orderTransactionList(transactionList)
    }

    fun orderTransactionList(transactionList: List<Transaction>): List<Transaction> {
        return transactionList.sortedByDescending { transaction -> transaction.transactionDate }
    }

    fun getTransactionById(transactionId: String): Transaction? {
        return transactionList.find { transaction -> transaction.id == transactionId }
    }
}