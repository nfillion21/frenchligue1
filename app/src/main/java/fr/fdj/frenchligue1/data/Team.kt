package fr.fdj.frenchligue1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey (autoGenerate = false) val idTeam: String,
    val strTeam: String,
    val strTeamLogo: String?
)