package fr.fdj.frenchligue1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import fr.fdj.frenchligue1.utilities.DATABASE_NAME
import fr.fdj.frenchligue1.utilities.LEAGUES_LIST_URL
import fr.fdj.frenchligue1.workers.LeaguesDatabaseWorker
import fr.fdj.frenchligue1.workers.LeaguesDatabaseWorker.Companion.LEAGUES_KEY_URL

@Database(entities = [League::class, Team::class, LeagueTeamCrossRef::class], version = 1, exportSchema = false)
abstract class FrenchLigue1RoomDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao
    abstract fun teamDao(): TeamDao
    abstract fun leagueTeamCrossRefDao(): LeagueTeamCrossRefDao

    companion object {
        @Volatile
        private var instance: FrenchLigue1RoomDatabase? = null

        fun getInstance(context: Context): FrenchLigue1RoomDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FrenchLigue1RoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FrenchLigue1RoomDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(LeagueDatabaseCallback(context))
                .build()
        }

        private class LeagueDatabaseCallback(
            private val context: Context
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val workManager = WorkManager.getInstance(context)
                val requestLeagues = OneTimeWorkRequestBuilder<LeaguesDatabaseWorker>()
                    .setInputData(workDataOf(LEAGUES_KEY_URL to LEAGUES_LIST_URL))
                    .build()
                workManager.enqueue(requestLeagues)
            }
        }
    }
}