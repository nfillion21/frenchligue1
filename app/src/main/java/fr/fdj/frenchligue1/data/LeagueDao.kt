package fr.fdj.frenchligue1.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from league order by idLeague")
    fun getLeagues(): Flow<List<League>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<League>)

    @Transaction
    @Query("select * from league where idLeague = :leagueId")
    fun getLeagueWithTeams(leagueId: String): Flow<LeagueWithTeams>
}
