package nz.co.asb

import nz.co.asb.model.Repository
import nz.co.asb.model.Transaction
import org.joda.time.format.DateTimeFormat
import org.junit.Assert
import org.junit.Test


class RepositoryUnitTest {

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
        Assert.assertTrue(
            sortedList[0] == transaction2
                    && sortedList[1] == transaction1
        )
    }
}