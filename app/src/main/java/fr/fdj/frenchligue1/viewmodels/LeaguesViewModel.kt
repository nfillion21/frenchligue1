package fr.fdj.frenchligue1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeaguesRepository
import fr.fdj.frenchligue1.preferences.UserPreferences
import fr.fdj.frenchligue1.preferences.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class LeaguesUiModel(
    val leagues: List<League>,
    val filterLeagues: String
)

@HiltViewModel
class LeaguesViewModel @Inject internal constructor(
    private val leaguesRepository: LeaguesRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val allLeagues: Flow<List<League>> = leaguesRepository.allLeagues

    // Every time the sort order, the show completed filter or the list of tasks emit,
    // we should recreate the list of tasks
    /*
    private val leaguesUiModelFlow = combine(
        heroRepository.allPlayers,
        userPreferencesRepository.userPreferencesFlow
    ) { players: List<League>, userPreferences: UserPreferences ->
        return@combine PlayersUiModel(
            leagues = filteredLeagues(
                players = players,
                showVillains = userPreferences.showVillains,
                sortOrder = userPreferences.sortOrder
            ),
            showVillains = userPreferences.showVillains,
            sortOrder = userPreferences.sortOrder
        )
    }
    */

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

    /*
    val playersUiModel: StateFlow<PlayersUiModel?> = playersUiModelFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null)
    */

    private fun filteredLeagues(leagues: List<League>, filterLeagues: String): List<League> {
        return if (filterLeagues.isNotEmpty()) {
            leagues.filter { it.strLeague.contains(other = filterLeagues, ignoreCase = true) }
        } else {
            emptyList()
        }
        //val filtered = if (showVillains) players.filter { it.side == "Villain" } else players
        //return filtered.sortedWith(sortOrder.comparator)
    }
}