package fr.fdj.frenchligue1.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(private val leagueDao: LeagueDao) {
    val allLeagues: Flow<List<League>> = leagueDao.getLeagues()
    fun getLeagueWithTeams(leagueId: String) = leagueDao.getLeagueWithTeams(leagueId)
}
