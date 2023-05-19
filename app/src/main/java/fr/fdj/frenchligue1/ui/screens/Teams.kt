package fr.fdj.frenchligue1.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeagueWithTeams

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Teams(
    leagueWithTeams: LeagueWithTeams,
    upPress: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(league = leagueWithTeams.league, upPress = upPress)

        val state = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            state = state,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
            content = {
                items(leagueWithTeams.teams) { team ->
                    SubcomposeAsyncImage(
                        model = team.strTeamBadge,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    league: League,
    upPress: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = league.strLeague)
        },
        navigationIcon = {
            IconButton(onClick = upPress) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        }
    )
}
