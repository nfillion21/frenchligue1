package fr.fdj.frenchligue1.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from team order by idTeam")
    fun getTeams(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teams: List<Team>)
}
