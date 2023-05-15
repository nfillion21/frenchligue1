package fr.fdj.frenchligue1.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.fdj.frenchligue1.data.FrenchLigue1RoomDatabase
import fr.fdj.frenchligue1.data.LeagueDao
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
}