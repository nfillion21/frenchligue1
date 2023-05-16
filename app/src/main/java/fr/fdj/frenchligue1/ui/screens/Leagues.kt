package fr.fdj.frenchligue1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import fr.fdj.frenchligue1.viewmodels.LeaguesUiModel

@Composable
fun Leagues(
    stateLeagues: LeaguesUiModel,
    selectLeague: (String) -> Unit,
    filterLeague: (String) -> Unit,
    modifier: Modifier
) {
    val filterLeaguesState = stateLeagues.filterLeagues
    val textState = remember { mutableStateOf(TextFieldValue(filterLeaguesState)) }
    Column(modifier = modifier.statusBarsPadding(),
    ) {
        SearchView(state = textState, onValueChange = filterLeague)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(stateLeagues.leagues) { league ->
                Text(
                    text = league.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { selectLeague(league.strLeague) })
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                        .wrapContentWidth(Alignment.Start)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onValueChange(value.text)
        },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        onValueChange("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape
    )
}