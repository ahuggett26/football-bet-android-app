package app.ahuggett.betsimapp.data

import app.ahuggett.betsimapp.data.OddsData
import app.ahuggett.betsimapp.data.teams.getTeamFromTla

val exampleOdds = listOf(
    OddsData("ghijkl", getTeamFromTla("LIV")!!, getTeamFromTla("BOU")!!, 1.3, 5.75, 8.5, "2025-08-15T19:00:00Z"),
    OddsData("abcdef", getTeamFromTla("BRI")!!, getTeamFromTla("FUL")!!, 1.91, 3.6, 3.9, "2025-08-16T14:00:00Z"),
    OddsData("mnopqr", getTeamFromTla("TOT")!!, getTeamFromTla("BUR")!!, 1.35, 5.0, 8.5, "2025-08-16T14:00:00Z"),
    OddsData("stuvwx", getTeamFromTla("ARS")!!, getTeamFromTla("LIV")!!, 3.3, 1.12, 3.7, "2025-08-17T20:00:00Z")
)