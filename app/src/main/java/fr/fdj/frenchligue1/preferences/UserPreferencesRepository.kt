package fr.fdj.frenchligue1.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

data class UserPreferences(
    val filterLeagues: String
)

/**
 * Class that handles saving and retrieving user preferences
 */
class UserPreferencesRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "UserPreferencesRepo"

    private object PreferencesKeys {
        val FILTER_LEAGUES = stringPreferencesKey("filter_leagues")
    }

    /**
     * Get the user preferences flow.
     */
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun updateFilterLeagues(filterLeagues: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.FILTER_LEAGUES] = filterLeagues
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val filterLeagues = preferences[PreferencesKeys.FILTER_LEAGUES] ?: ""
        return UserPreferences(filterLeagues)
    }
}
