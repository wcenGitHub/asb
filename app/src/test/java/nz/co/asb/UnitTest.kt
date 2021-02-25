package nz.co.asb

import net.danlew.android.joda.JodaTimeAndroid
import nz.co.asb.di.AppSessionComponent
import nz.co.asb.di.AppSingletonComponent
import nz.co.asb.model.Repository
import nz.co.asb.model.Transaction
import nz.co.asb.viewmodels.MainViewModel
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class UnitTest {
    
    @Test
    fun test_repository_sortTransactionOrder() {
        val dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ")
        val transaction1 = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-03T00:06:37+13:00"),
            "summary1",
            0.0,
            0.0
        )

        val transaction2 = Transaction(
            "1",
            dateFormat.parseDateTime("2021-02-05T00:06:37+13:00"),
            "summary1",
            0.0,
            0.0
        )

        val repo = Repository()

        val sortedList = repo.orderTransactionList(listOf(transaction1, transaction2))
        Assert.assertTrue(sortedList[0] == transaction2
                && sortedList[1] == transaction1)
    }
}