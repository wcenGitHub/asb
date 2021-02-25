package nz.co.asb

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import nz.co.asb.ui.MainActivity
import org.junit.Before
import org.junit.Rule

open class BaseTestTemplate {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var context: Context

    @Before
    fun beforeTesting() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("settings put secure autofill_service null")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("settings put global window_animation_scale 0")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("settings put global transition_animation_scale 0")
        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("settings put global animator_duration_scale 0")

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        clearSharedPreferencesData(context)

        try {
            ActivityScenario.launch(MainActivity::class.java)
        } catch (ex: Exception) {
            // From the documentation: It is recommended to wrap the launch in a try-block, so the Activity is closed automatically.
        }

        // This line may fail sometime, please run again if it fails
        rule.scenario.onActivity {
            this.context = it
        }
    }

    private fun clearSharedPreferencesData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()
    }
}