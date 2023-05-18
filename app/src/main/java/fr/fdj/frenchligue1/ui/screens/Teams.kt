package fr.fdj.frenchligue1.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import fr.fdj.frenchligue1.R
import fr.fdj.frenchligue1.data.LeagueWithTeams

@Composable
fun Teams(
    leagueWithTeams: LeagueWithTeams,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.statusBarsPadding()
    ) {
        items(leagueWithTeams.teams) { league ->
            Text(
                text = league.toString(),
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
                model = league.strTeamBadge,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
        }
    }
}