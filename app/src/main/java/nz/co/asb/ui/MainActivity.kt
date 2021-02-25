package nz.co.asb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.coroutineScope
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import nz.co.asb.R
import nz.co.asb.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.transactionListLiveData.observe(this, {

        })

        lifecycle.coroutineScope.launch {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchTransactions()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}