package nz.co.asb

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import nz.co.asb.ui.TransactionListAdapter
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Test

class TransactionDetailActivityTest: BaseTestTemplate() {
    @Test
    fun screenElementsTest() {
        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)
        Espresso.onView(withId(R.id.transactionRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TransactionListAdapter.ViewHolder>(0, ViewActions.click()))

        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)

        Espresso.onView(withText(context.getString(R.string.transactionIdFormat, "2")))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.amount))
            .check(ViewAssertions.matches(Matchers.allOf(isDisplayed(), withText("-$8.00"))))
        Espresso.onView(withId(R.id.gst))
            .check(ViewAssertions.matches(Matchers.allOf(isDisplayed(), withText("GST: $1.20"))))
        Espresso.onView(withId(R.id.summery))
            .check(ViewAssertions.matches(Matchers.allOf(isDisplayed(), withText("Parking Auckland"))))

        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TransactionListAdapter.ViewHolder>(1, ViewActions.click()))
        Espresso.onView(withText(context.getString(R.string.transactionIdFormat, "3")))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.amount))
            .check(ViewAssertions.matches(Matchers.allOf(isDisplayed(), withText("+$1.00"))))
        Espresso.onView(withId(R.id.gst))
            .check(ViewAssertions.matches(CoreMatchers.not(isDisplayed())))
        Espresso.onView(withId(R.id.summery))
            .check(ViewAssertions.matches(Matchers.allOf(isDisplayed(), withText("Tips"))))
    }

    @Test
    fun screenNavigationTest() {
        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)
        Espresso.onView(withText(R.string.app_name))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TransactionListAdapter.ViewHolder>(0, ViewActions.click()))

        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)

        Espresso.onView(withText(R.string.transactionDetail))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withContentDescription("Navigate up")).perform(ViewActions.click())

        Espresso.onView(withText(R.string.app_name))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.transactionRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TransactionListAdapter.ViewHolder>(0, ViewActions.click()))

        Thread.sleep(Constants.WAIT_TIME_MILLISECOND_LONG)

        Espresso.onView(withText(R.string.transactionDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()

        Espresso.onView(withText(R.string.app_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}