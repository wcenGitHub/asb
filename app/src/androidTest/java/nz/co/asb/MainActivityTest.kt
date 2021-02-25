package nz.co.asb

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import nz.co.asb.ui.TransactionListAdapter
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest: BaseTestTemplate() {
    @Test
    fun screenElementsTest() {
        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)
        Espresso.onView(withText(R.string.app_name))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .check(ViewAssertions.matches(CustomMatcher.recyclerViewHasNItems(3)))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .check(
                ViewAssertions.matches(CustomMatcher.atPosition(0,
                    ViewMatchers.hasDescendant(withText("Parking Auckland"))
                )))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .check(
                ViewAssertions.matches(CustomMatcher.atPosition(0,
                    ViewMatchers.hasDescendant(Matchers.allOf(withId(R.id.gst), isDisplayed()))
                )))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .check(
                ViewAssertions.matches(CustomMatcher.atPosition(1,
                    ViewMatchers.hasDescendant(Matchers.allOf(withId(R.id.gst), CoreMatchers.not(isDisplayed())))
                )))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TransactionListAdapter.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)
        Espresso.onView(withText(R.string.transactionDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}