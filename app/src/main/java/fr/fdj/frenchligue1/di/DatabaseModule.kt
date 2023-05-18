package fr.fdj.frenchligue1.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.fdj.frenchligue1.data.FrenchLigue1RoomDatabase
import fr.fdj.frenchligue1.data.LeagueDao
import fr.fdj.frenchligue1.data.LeagueTeamCrossRefDao
import fr.fdj.frenchligue1.data.TeamDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FrenchLigue1RoomDatabase {
        return FrenchLigue1RoomDatabase.getInstance(context)
    }

    @Provides
    fun provideLeagueDao(appDatabase: FrenchLigue1RoomDatabase): LeagueDao {
        return appDatabase.leagueDao()
    }

    @Provides
    fun provideTeamDao(appDatabase: FrenchLigue1RoomDatabase): TeamDao {
        return appDatabase.teamDao()
    }

    @Provides
    fun provideLeagueTeamCrossRefDao(appDatabase: FrenchLigue1RoomDatabase): LeagueTeamCrossRefDao {
        return appDatabase.leagueTeamCrossRefDao()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("preferences")
            }
        )
}