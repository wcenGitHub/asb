package nz.co.asb.model

import androidx.lifecycle.MutableLiveData
import nz.co.asb.di.SessionScope
import javax.inject.Inject

@SessionScope
class Repository @Inject constructor() {

    @Inject
    lateinit var dataSource: DataSource

    var posts: MutableList<Transaction> = mutableListOf()

    val transactionListLiveData = MutableLiveData<List<Transaction>>()

    suspend fun fetchTransactions() {
        posts = dataSource.fetchTransactions().toMutableList()
        transactionListLiveData.value = orderTransactionList(posts)
    }

    fun orderTransactionList(transactionList: List<Transaction>): List<Transaction> {
        return transactionList.sortedByDescending { transaction -> transaction.transactionDate }
    }
}