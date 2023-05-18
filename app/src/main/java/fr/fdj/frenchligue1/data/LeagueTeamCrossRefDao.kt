package fr.fdj.frenchligue1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LeagueTeamCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagueTeamCrossRef: List<LeagueTeamCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(leagueTeamCrossRef: LeagueTeamCrossRef)
}
