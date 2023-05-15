package fr.fdj.frenchligue1.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesRepository @Inject constructor(private val leagueDao: LeagueDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allLeagues: Flow<List<League>> = leagueDao.getLeagues()
}
