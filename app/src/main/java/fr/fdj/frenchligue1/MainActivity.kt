package fr.fdj.frenchligue1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.AndroidEntryPoint
import fr.fdj.frenchligue1.ui.BuilderNavGraph
import fr.fdj.frenchligue1.ui.theme.FrenchLigue1Theme
import fr.fdj.frenchligue1.utilities.LEAGUES_LIST_URL
import fr.fdj.frenchligue1.workers.LeaguesDatabaseWorker
import fr.fdj.frenchligue1.workers.LeaguesDatabaseWorker.Companion.LEAGUES_KEY_URL

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrenchLigue1Theme {
                // A surface container using the 'background' color from the theme
                /*
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val workManager = WorkManager.getInstance(this)
                    val requestTitles = OneTimeWorkRequestBuilder<LeaguesDatabaseWorker>()
                        .setInputData(workDataOf(LEAGUES_KEY_URL to LEAGUES_LIST_URL))
                        .build()
                    workManager.enqueue(requestTitles)
                    Greeting("Android")
                }
                */

                val navController = rememberNavController()
                Scaffold { innerPaddingModifier ->
                    BuilderNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPaddingModifier)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrenchLigue1Theme {
        Greeting("Android")
    }
}