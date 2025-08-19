package app.ahuggett.betsimapp.data.bet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userBets")
data class Bet(
    @PrimaryKey
    val matchId: String,
    val betType: BetType,
    val odds: Double
)

enum class BetType {
    HOME_WIN,
    DRAW,
    AWAY_WIN
}