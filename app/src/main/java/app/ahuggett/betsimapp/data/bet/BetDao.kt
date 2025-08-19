package app.ahuggett.betsimapp.data.bet

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface BetDao {
    @Query("SELECT * FROM userBets")
    fun getAllBets(): Flow<List<Bet>>

    @Query("SELECT * FROM userBets WHERE matchId = :matchId LIMIT 1")
    suspend fun getBetForMatch(matchId: String): Bet?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBet(bet: Bet)

    @Query("DELETE FROM userBets WHERE matchId = :matchId")
    suspend fun deleteBetByMatchId(matchId: String)
}