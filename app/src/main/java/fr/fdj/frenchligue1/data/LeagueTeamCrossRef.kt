package fr.fdj.frenchligue1.data

import androidx.room.*

@Entity(primaryKeys = ["idLeague", "idTeam"])
data class LeagueTeamCrossRef(
    val idLeague: String,
    val idTeam: String,
)