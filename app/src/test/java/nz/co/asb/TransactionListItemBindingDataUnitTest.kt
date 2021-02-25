package nz.co.asb

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import nz.co.asb.model.Transaction
import nz.co.asb.ui.TransactionListItemBindingData
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class TransactionListItemBindingDataUnitTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun test_summary() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val summary = "summary1"
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            summary,
            1.0,
            0.0
        )

        val data = TransactionListItemBindingData(transaction, context)

        assert(
            data.summary == transaction.summary &&
                    data.summary == summary
        )
    }

    @Test
    fun test_dateDisplayString() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val date = dateFormat.parseDateTime("2021-02-03T00:06:37+13:00")
        val transaction = Transaction(
            "1",
            date,
            "summary",
            1.0,
            0.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        val dateDisplayFormat: DateTimeFormatter = DateTimeFormat.forPattern("dd MMM yyyy")

        assert(data.dateDisplayString() == dateDisplayFormat.print(date))
    }

    @Test
    fun test_gstDisplayString_creditTransactionReturnNullString() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            0.0,
            1.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        Assert.assertNull(data.gstDisplayString())
    }

    @Test
    fun test_gstDisplayString_dabitTransactionReturnValidString() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            1.0,
            0.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        Assert.assertNotNull(data.gstDisplayString())

        val locale = Locale(Locale.getDefault().language, "NZ")

        val gstAmount = transaction.debit * Constants.GST_RATE
        val displayGstString =
            context.getString(R.string.gstAmountFormat, gstAmount.toCurrencyFormattedString(locale))
        assert(displayGstString == data.gstDisplayString())
    }

    @Test
    fun test_amountDisplayString_dabitTransactionReturnNegativeString() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            1.0,
            0.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        val locale = Locale(Locale.getDefault().language, "NZ")

        val displayAmountString = context.getString(
            R.string.debitAmountFormat,
            transaction.debit.toCurrencyFormattedString(locale)
        )
        assert(displayAmountString == data.amountDisplayString())
    }

    @Test
    fun test_amountDisplayString_creditTransactionReturnPositiveString() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            0.0,
            1.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        val locale = Locale(Locale.getDefault().language, "NZ")

        val displayAmountString = context.getString(
            R.string.creditAmountFormat,
            transaction.credit.toCurrencyFormattedString(locale)
        )
        assert(displayAmountString == data.amountDisplayString())
    }

    @Test
    fun test_gstVisibility_creditTransactionHideGst() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            0.0,
            1.0
        )
        val data = TransactionListItemBindingData(transaction, context)


        assert(data.gstVisibility() == View.GONE)
    }

    @Test
    fun test_gstVisibility_debitTransactionShowGst() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            1.0,
            0.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        assert(data.gstVisibility() == View.VISIBLE)
    }

    @Test
    fun test_amountTextColor_creditTransactionAmountTextColorIsGreen() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            0.0,
            1.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        assert(data.amountTextColor() == context.getColor(R.color.green))
    }

    @Test
    fun test_amountTextColor_debitTransactionAmountTextColorIsRed() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary",
            1.0,
            0.0
        )
        val data = TransactionListItemBindingData(transaction, context)

        assert(data.amountTextColor() == context.getColor(R.color.red))
    }
}