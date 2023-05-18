package fr.fdj.frenchligue1.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import fr.fdj.frenchligue1.data.FrenchLigue1RoomDatabase
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeagueTeamCrossRef
import fr.fdj.frenchligue1.data.Team
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamsDatabaseWorker(
    context: Context, workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val client = HttpClient(Android)
            val url = inputData.getString(TEAMS_KEY_URL)
            val strLeague = inputData.getString(STR_LEAGUE_KEY_URL)
            val idLeague = inputData.getString(ID_LEAGUE_KEY_URL)
            val result: HttpResponse = client.get(url+strLeague)

            if (result.status == HttpStatusCode.OK) {
                val json = Gson().fromJson(result.bodyAsText(), JsonObject::class.java)
                val teamType = object : TypeToken<List<Team>>() {}.type
                val teamList: List<Team> = Gson().fromJson(json["teams"], teamType)
                val leagueTeamCrossRefMutableList =
                    teamList.map { LeagueTeamCrossRef(idLeague = idLeague!!, idTeam = it.idTeam) }

                val database = FrenchLigue1RoomDatabase.getInstance(applicationContext)
                database.leagueTeamCrossRefDao().insertAll(leagueTeamCrossRefMutableList)
                database.teamDao().insertAll(teamList)

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
        private const val TAG = "TeamsDatabaseWorker"
        const val TEAMS_KEY_URL = "TEAMS_KEY_URL"
        const val STR_LEAGUE_KEY_URL = "STR_LEAGUE_KEY_URL"
        const val ID_LEAGUE_KEY_URL = "ID_LEAGUE_KEY_URL"
    }
}
