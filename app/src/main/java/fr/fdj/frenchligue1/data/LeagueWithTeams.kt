package fr.fdj.frenchligue1.data

import androidx.room.*

data class LeagueWithTeams(
    @Embedded val league: League,
    @Relation(
        parentColumn = "idLeague",
        entityColumn = "idTeam",
        associateBy = Junction(LeagueTeamCrossRef::class)
    )
    val teams: List<Team>
)
