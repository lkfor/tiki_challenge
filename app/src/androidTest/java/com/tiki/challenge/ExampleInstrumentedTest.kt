package com.tiki.challenge

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tiki.challenge.utils.StringUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.tiki.challenge", appContext.packageName)
    }

    @Test
    fun testSplitString() {
        val source = "nguyễn nhật ánh"
        val expectation = "nguyễn\nnhật ánh"
        val result = StringUtils.splitString(source)
        assertEquals(result, expectation)
    }
}
