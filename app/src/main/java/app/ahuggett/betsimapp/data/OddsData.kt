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
    val homeOddsFraction = fractionString(homeOdds)
    val drawOddsFraction = fractionString(drawOdds)
    val awayOddsFraction = fractionString(awayOdds)
}

fun fractionString(odds: Double): String {
    if (odds % 1 == 0.0) {
        return "${odds.toInt() - 1} / 1"
    }
    val fraction = Fraction(odds - 1)
    return fraction.toString()
}
