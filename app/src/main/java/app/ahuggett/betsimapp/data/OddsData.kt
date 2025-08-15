package app.ahuggett.betsimapp.data

import app.ahuggett.betsimapp.data.teams.Team
import org.apache.commons.math3.fraction.Fraction
import java.time.Instant
import java.time.ZoneId

data class OddsData(
    val matchId: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeOdds: Double,
    val drawOdds: Double,
    val awayOdds: Double,
    val kickoffUtc: String) {

    val kickoffDate = Instant.parse(kickoffUtc).atZone(ZoneId.systemDefault())
    val homeOddsFraction = Fraction(homeOdds - 1).toString()
    val drawOddsFraction = Fraction(drawOdds - 1).toString()
    val awayOddsFraction = Fraction(awayOdds - 1).toString()
}
