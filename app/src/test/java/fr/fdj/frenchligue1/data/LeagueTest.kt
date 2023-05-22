package fr.fdj.frenchligue1.data

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class LeagueTest {

    private lateinit var league: League

    @Before
    fun setup() {
        league = League(
            idLeague = "",
            strLeague = "accusamus beatae ad facilis cum similique qui sunt",
            strSport = "",
            strLeagueAlternate = null
        )
    }

    @Test
    fun test_toString() {
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", league.strLeague)
    }
}
