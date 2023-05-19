package fr.fdj.frenchligue1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import fr.fdj.frenchligue1.data.League
import fr.fdj.frenchligue1.data.LeagueWithTeams

@Composable
fun Teams(
    leagueWithTeams: LeagueWithTeams,
    upPress: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(league = leagueWithTeams.league, upPress = upPress)
        LazyColumn {
            items(leagueWithTeams.teams) { team ->
                Text(
                    text = team.strTeam,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                        .wrapContentWidth(Alignment.Start)
                )
                SubcomposeAsyncImage(
                    model = team.strTeamBadge,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = null
                )
            }
        }
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
