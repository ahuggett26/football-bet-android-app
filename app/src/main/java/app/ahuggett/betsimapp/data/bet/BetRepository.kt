package app.ahuggett.betsimapp.data.bet

import kotlinx.coroutines.flow.Flow

class BetRepository(private val betDao: BetDao) {
    fun getAllBets(): Flow<List<Bet>> = betDao.getAllBets()

    suspend fun getBet(matchId: String): Bet? = betDao.getBetForMatch(matchId)

    suspend fun saveBet(bet: Bet) = betDao.insertBet(bet)

    suspend fun removeBet(matchId: String) = betDao.deleteBetByMatchId(matchId)
}