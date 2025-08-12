package app.ahuggett.betsimapp

import org.apache.commons.math3.fraction.Fraction
import java.time.Instant
import java.time.ZoneId

data class OddsData(
    val matchId: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeOdds: Double,
    val drawOdds: Double,
    val awayOdds: Double,
    val kickoffUtc: String) {

    val kickoffDate = Instant.parse(kickoffUtc).atZone(ZoneId.systemDefault())
    val homeOddsFraction = Fraction(homeOdds - 1).toString()
    val drawOddsFraction = Fraction(drawOdds - 1).toString()
    val awayOddsFraction = Fraction(awayOdds - 1).toString()
}
