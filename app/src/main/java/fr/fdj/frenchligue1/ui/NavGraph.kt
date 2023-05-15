package fr.fdj.frenchligue1.ui

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.fdj.frenchligue1.ui.MainRoutes.B_LEAGUES_ROUTE
import fr.fdj.frenchligue1.ui.MainRoutes.LEAGUE_DETAILS_ID_KEY
import fr.fdj.frenchligue1.ui.screens.Leagues
import fr.fdj.frenchligue1.viewmodels.LeaguesViewModel

/**
 * Destinations used in the ([FrenchLigue1]).
 */
object MainRoutes {
    const val LEAGUES_ROUTE = "leagues"
    const val LEAGUE_DETAILS_ROUTE = "league"
    const val LEAGUE_DETAILS_ID_KEY = "leagueId"
    const val B_LEAGUES_ROUTE = "frenchligue1/leagues"
}

@Composable
fun BuilderNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainRoutes.LEAGUES_ROUTE,
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = MainRoutes.LEAGUES_ROUTE,
            startDestination = B_LEAGUES_ROUTE
        ) {

            composable(B_LEAGUES_ROUTE) { navBackStackEntry ->

                val leaguesViewModel: LeaguesViewModel = hiltViewModel()
                val stateLeagues by leaguesViewModel.allLeagues.collectAsState(initial = emptyList())
                Leagues(
                    modifier = modifier,
                    stateLeagues = stateLeagues,
                    selectLeague = { league ->
                        actions.openPlayerDetail(
                            league,
                            navBackStackEntry
                        )
                    }
                )
            }
        }
        composable(
            "${MainRoutes.LEAGUE_DETAILS_ROUTE}/{$LEAGUE_DETAILS_ID_KEY}",
            arguments = listOf(
                navArgument(LEAGUE_DETAILS_ID_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry: NavBackStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val currentCourseId = arguments.getString(LEAGUE_DETAILS_ID_KEY)
            if (currentCourseId != null) {

                /*
                val playerViewModel: HeroViewModel = hiltViewModel()
                val cartViewModel: CartViewModel = hiltViewModel()
                PlayerDetails(
                    playerId = currentCourseId,
                    playerViewModel = playerViewModel,
                    cartViewModel = cartViewModel,
                    addToCart = { playerId ->
                        cartViewModel.insert(PlayerCartCrossRef(playerId,"tmnt"))
                    },
                    upPress = { actions.upPress(backStackEntry) }
                )
                */
            }
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    // Used from COURSES_ROUTE
    val openPlayerDetail = { newPlayerId: String, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainRoutes.LEAGUE_DETAILS_ROUTE}/$newPlayerId")
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
