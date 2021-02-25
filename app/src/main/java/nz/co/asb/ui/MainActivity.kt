package nz.co.asb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import nz.co.asb.R
import nz.co.asb.model.Transaction
import nz.co.asb.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TransactionListAdapter.TransactionSelectionListener {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.isEnabled = false

        viewModel.transactionListLiveData.observe(this, {
            val adapter = transactionRecyclerView.adapter as TransactionListAdapter
            adapter.transactionList = it
            adapter.notifyDataSetChanged()
        })

        transactionRecyclerView.apply {
            adapter = TransactionListAdapter(emptyList(), this@MainActivity)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.errorData.observe(this, {
            Toast.makeText(this, R.string.errorMessage, Toast.LENGTH_LONG).show()
        })

        lifecycle.coroutineScope.launch {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchTransactions()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onTransactionSelected(transaction: Transaction) {
        val intent = Intent(this@MainActivity, TransactionDetailActivity::class.java)
        intent.putExtra(TransactionDetailActivity.TRANSACTION_ID_EXTRA_KEY, transaction.id)
        startActivity(intent)
        overridePendingTransition(R.anim.activity_open_translate, R.anim.stay)
    }
}