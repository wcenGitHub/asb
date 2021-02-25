package nz.co.asb.ui

import android.content.Context
import android.view.View
import nz.co.asb.Constants
import nz.co.asb.R
import nz.co.asb.model.Transaction
import nz.co.asb.toCurrencyFormattedString
import java.util.*

class TransactionUiBindingData(
    private val transaction: Transaction,
    private val context: Context
) {

    val transactionIdDisplayString: String get() {
        return context.getString(R.string.transactionIdFormat, transaction.id)
    }

    val summary: String get() {
        return transaction.summary
    }

    fun dateDisplayString(): String {
        return Constants.DATE_DISPLAY_FORMAT.print(transaction.transactionDate)
    }

    fun amountDisplayString(): String {
        val locale = Locale(Locale.getDefault().language, "NZ")
        return if (isDebit) {
            context.getString(
                R.string.debitAmountFormat, transaction.debit.toCurrencyFormattedString(
                    locale
                )
            )
        } else {
            context.getString(
                R.string.creditAmountFormat, transaction.credit.toCurrencyFormattedString(
                    locale
                )
            )
        }
    }

    fun gstDisplayString(): String? {
        return if (isDebit) {
            val gstAmount = transaction.debit * Constants.GST_RATE
            val locale = Locale(Locale.getDefault().language, "NZ")
            context.getString(R.string.gstAmountFormat, gstAmount.toCurrencyFormattedString(locale))
        } else {
            null
        }
    }

    fun gstVisibility(): Int {
        return if (transaction.debit != 0.0) View.VISIBLE else View.GONE
    }

    fun amountTextColor(): Int {
        if (isDebit) {
            return context.getColor(R.color.red)
        } else {
            return context.getColor(R.color.green)
        }
    }

    private val isDebit: Boolean get() {
        return transaction.debit != 0.0
    }
}