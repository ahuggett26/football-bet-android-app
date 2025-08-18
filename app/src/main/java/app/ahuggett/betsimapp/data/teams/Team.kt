package app.ahuggett.betsimapp.data.teams

import android.graphics.Color
import app.ahuggett.betsimapp.R

fun getTeamFromTla(tla: String): Team? {
    return when (tla) {
        "ARS" -> Team("ARS", "Arsenal", R.drawable.ars, 0xe20613)
        "BOU" -> Team("BOU", "Bournemouth", R.drawable.bou, 0xce0a17)
        "BRI" -> Team("BRI", "Brighton", R.drawable.bri, 0x0054a5)
        "BUR" -> Team("BUR", "Burnley", R.drawable.bur, 0x81204c)
        "FUL" -> Team("FUL", "Fulham", R.drawable.ful, 0x000)
        "LIV" -> Team("LIV", "Liverpool", R.drawable.liv, 0xd10011)
        "TOT" -> Team("TOT", "Tottenham", R.drawable.tot, 0x000a3c)
        else -> null
    }
}

data class Team(val tla: String, val displayName: String, val badge: Int, val primaryColor: Int)
