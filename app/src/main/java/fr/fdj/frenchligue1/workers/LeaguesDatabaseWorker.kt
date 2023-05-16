package fr.fdj.frenchligue1.workers

import android.content.Context
import android.location.Address
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import fr.fdj.frenchligue1.data.FrenchLigue1RoomDatabase
import fr.fdj.frenchligue1.data.League
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeaguesDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val client = HttpClient(Android)
            val url = inputData.getString(LEAGUES_KEY_URL)
            val result: HttpResponse = client.get(url!!)

            if (result.status == HttpStatusCode.OK) {
                val json = Gson().fromJson(result.bodyAsText(), JsonObject::class.java)
                val leagueType = object : TypeToken<List<League>>() {}.type
                val leagueList: List<League> = Gson().fromJson(json["leagues"], leagueType)

                val database = FrenchLigue1RoomDatabase.getInstance(applicationContext)
                database.leagueDao().insertAll(leagueList)

                Result.success()
            } else {
                Log.e(TAG, "Error seeding database - no valid url")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "LeagueDatabaseWorker"
        const val LEAGUES_KEY_URL = "LEAGUES_KEY_URL"
    }
}
