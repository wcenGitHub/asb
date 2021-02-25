package nz.co.asb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_transaction_detail.*
import nz.co.asb.R
import nz.co.asb.databinding.ActivityTransactionDetailBinding
import nz.co.asb.viewmodels.TransactionDetailViewModel
import javax.inject.Inject

class TransactionDetailActivity : AppCompatActivity() {

    companion object {
        const val TRANSACTION_ID_EXTRA_KEY = "transactionId"
    }

    @Inject
    lateinit var viewModel: TransactionDetailViewModel

    private lateinit var binding: ActivityTransactionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_detail)

        title = getString(R.string.transactionDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val transactionId = intent.getStringExtra(TRANSACTION_ID_EXTRA_KEY)?.let { transactionId ->
            viewModel.getTransactionById(transactionId)?.let { transaction ->
                binding.data = TransactionUiBindingData(transaction, this)
                binding.executePendingBindings()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.stay, R.anim.activity_close_translate)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}