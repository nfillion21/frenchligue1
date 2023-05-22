package fr.fdj.frenchligue1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class League(
    @PrimaryKey(autoGenerate = false) val idLeague: String,
    val strLeague: String,
    val strSport: String,
    val strLeagueAlternate: String?
) {
    override fun toString() = strLeague
}