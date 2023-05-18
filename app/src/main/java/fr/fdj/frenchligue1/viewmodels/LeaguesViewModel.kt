package fr.fdj.frenchligue1.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeagueWithTeams
import fr.fdj.frenchligue1.data.LeaguesRepository
import fr.fdj.frenchligue1.preferences.UserPreferences
import fr.fdj.frenchligue1.preferences.UserPreferencesRepository
import fr.fdj.frenchligue1.utilities.TEAMS_LIST_URL
import fr.fdj.frenchligue1.workers.TeamsDatabaseWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LeaguesUiModel(
    val leagues: List<League>,
    val filterLeagues: String
)

@HiltViewModel
class LeaguesViewModel @Inject internal constructor(
    private val leaguesRepository: LeaguesRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    private val allLeagues: Flow<List<League>> = leaguesRepository.allLeagues
    val leaguesUiModelFlow: Flow<LeaguesUiModel> =
        allLeagues.combine(userPreferencesRepository.userPreferencesFlow) { leagues: List<League>, userPreferences: UserPreferences ->
            LeaguesUiModel(
                leagues = filteredLeagues(
                    leagues = leagues,
                    filterLeagues = userPreferences.filterLeagues
                ),
                filterLeagues = userPreferences.filterLeagues
            )
        }

    private fun filteredLeagues(leagues: List<League>, filterLeagues: String): List<League> {
        return if (filterLeagues.isNotEmpty()) {
            leagues.filter { it.strLeague.contains(other = filterLeagues, ignoreCase = true) }
        } else {
            emptyList()
        }
    }

    fun updateFilterLeagues(filterLeagues: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateFilterLeagues(filterLeagues)
        }
    }

    //TODO set league parameter
    fun launchWorkManager(context:Context) {
        val workManager = WorkManager.getInstance(context)
        val requestTeams = OneTimeWorkRequestBuilder<TeamsDatabaseWorker>()
            .setInputData(workDataOf(TeamsDatabaseWorker.TEAMS_KEY_URL to TEAMS_LIST_URL))
            .build()
        workManager.enqueue(requestTeams)
    }

    fun getLeagueWithTeams(leagueId:String): Flow<LeagueWithTeams> {
        return leaguesRepository.getLeagueWithTeams(leagueId)
    }
}