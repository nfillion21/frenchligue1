package fr.fdj.frenchligue1.workers

import android.content.Context
import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import fr.fdj.frenchligue1.utilities.LEAGUES_LIST_URL
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

class LeaguesDatabaseWorkerTest {
    private lateinit var workManager: WorkManager
    private lateinit var context: Context
    private lateinit var configuration: Configuration

    @Before
    fun setup() {
        // Configure WorkManager
        configuration = Configuration.Builder()
            // Set log level to Log.DEBUG to make it easier to debug
            .setMinimumLoggingLevel(Log.DEBUG)
            // Use a SynchronousExecutor here to make it easier to write tests
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        context = InstrumentationRegistry.getInstrumentation().targetContext
        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testRefreshMainDataWork() {
        // Get the ListenableWorker
        val worker = TestListenableWorkerBuilder<LeaguesDatabaseWorker>(
            context = context,
            inputData = workDataOf(LeaguesDatabaseWorker.LEAGUES_KEY_URL to LEAGUES_LIST_URL)
        ).build()

        // Start the work synchronously
        val result = worker.startWork().get()

        assertThat(result, CoreMatchers.`is`(ListenableWorker.Result.success()))
    }
}