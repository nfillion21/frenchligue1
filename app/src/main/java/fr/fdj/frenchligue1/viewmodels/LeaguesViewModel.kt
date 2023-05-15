package fr.fdj.frenchligue1.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeaguesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class PlayersUiModel(
    val leagues: List<League>
)

@HiltViewModel
class LeaguesViewModel @Inject internal constructor(
    private val leaguesRepository: LeaguesRepository,
) : ViewModel()
{
    val allLeagues: Flow<List<League>> = leaguesRepository.allLeagues

    // Every time the sort order, the show completed filter or the list of tasks emit,
    // we should recreate the list of tasks
    /*
    private val playersUiModelFlow = combine(
        heroRepository.allPlayers,
        userPreferencesRepository.userPreferencesFlow
    ) { players: List<League>, userPreferences: UserPreferences ->
        return@combine PlayersUiModel(
            leagues = filteredPlayers(
                players = players,
                showVillains = userPreferences.showVillains,
                sortOrder = userPreferences.sortOrder
            ),
            showVillains = userPreferences.showVillains,
            sortOrder = userPreferences.sortOrder
        )
    }
    */

    /*
    val playersUiModel:StateFlow<PlayersUiModel?> = playersUiModelFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null)

    private fun filteredPlayers(players:List<League>, showVillains: Boolean, sortOrder: SortOrder): List<Hero> {
        val filtered = if (showVillains) players.filter { it.side == "Villain" } else players
        return filtered.sortedWith(sortOrder.comparator)
    }
    */
}