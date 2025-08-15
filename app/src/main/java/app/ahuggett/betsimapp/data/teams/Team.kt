package app.ahuggett.betsimapp.data.teams

import android.graphics.Color
import app.ahuggett.betsimapp.R

fun getTeamFromTla(tla: String): Team? {
    return when (tla) {
        "ARS" -> Team("ARS", "Arsenal", R.drawable.ars, Color.valueOf(0xe20613))
        "BOU" -> Team("BOU", "Bournemouth", R.drawable.bou, Color.valueOf(0xce0a17))
        "BRI" -> Team("BRI", "Brighton", R.drawable.bri, Color.valueOf(0x0054a5))
        "BUR" -> Team("BUR", "Burnley", R.drawable.bur, Color.valueOf(0x81204c))
        "FUL" -> Team("FUL", "Fulham", R.drawable.ful, Color.valueOf(0x000))
        "LIV" -> Team("LIV", "Liverpool", R.drawable.liv, Color.valueOf(0xd10011))
        "TOT" -> Team("TOT", "Tottenham", R.drawable.tot, Color.valueOf(0x000a3c))
        else -> null
    }
}

data class Team(val tla: String, val displayName: String, val badge: Int, val primaryColor: Color)
