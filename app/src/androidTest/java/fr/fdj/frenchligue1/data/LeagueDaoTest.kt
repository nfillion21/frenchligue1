package fr.fdj.frenchligue1.data

import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueDaoTest {
    private lateinit var database: FrenchLigue1RoomDatabase
    private lateinit var leagueDao: LeagueDao

    private val leagueA = League(
        idLeague = "1",
        strLeague = "",
        strSport = "",
        strLeagueAlternate = null
    )

    private val leagueB = League(
        idLeague = "2",
        strLeague = "",
        strSport = "",
        strLeagueAlternate = null
    )

    private val leagueC = League(
        idLeague = "3",
        strLeague = "",
        strSport = "",
        strLeagueAlternate = null
    )

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database =
            Room.inMemoryDatabaseBuilder(context, FrenchLigue1RoomDatabase::class.java).build()
        leagueDao = database.leagueDao()

        // Insert leagues in non-alphabetical order to test that results are sorted by id
        leagueDao.insertAll(listOf(leagueB, leagueC, leagueA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetLeagues() = runBlocking {
        val leagueList = leagueDao.getLeagues().first()
        assertThat(leagueList.size, Matchers.equalTo(3))

        // Ensure league list is sorted by id
        assertThat(leagueList[0], Matchers.equalTo(leagueA))
        assertThat(leagueList[1], Matchers.equalTo(leagueB))
        assertThat(leagueList[2], Matchers.equalTo(leagueC))
    }
}