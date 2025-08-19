package app.ahuggett.betsimapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import app.ahuggett.betsimapp.data.OddsData
import app.ahuggett.betsimapp.R
import app.ahuggett.betsimapp.data.bet.Bet
import app.ahuggett.betsimapp.data.bet.BetRepository
import app.ahuggett.betsimapp.data.bet.BetType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class MatchAdapter(
    private val matches: List<OddsData>,
    private val betRepository: BetRepository,
    private val coroutineScope: CoroutineScope
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MATCH = 1
        private const val VIEW_TYPE_FOOTER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < matches.size) VIEW_TYPE_MATCH else VIEW_TYPE_FOOTER
    }

    override fun getItemCount(): Int = matches.size + 1 // +1 for footer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MATCH -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.match_item, parent, false)
                MatchViewHolder(view, betRepository, coroutineScope)
            }
            VIEW_TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.matches_footer, parent, false)
                FooterViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MatchViewHolder -> {
                val match = matches[position]
                holder.bind(match)
            }
            is FooterViewHolder -> {
                // Footer doesn't need binding
            }
        }
    }

    class MatchViewHolder(
        itemView: View,
        private val betRepository: BetRepository,
        private val coroutineScope: CoroutineScope
    ) : RecyclerView.ViewHolder(itemView) {
        private val homeBadge: ImageView = itemView.findViewById(R.id.home_badge)
        private val awayBadge: ImageView = itemView.findViewById(R.id.away_badge)
        private val teams: TextView = itemView.findViewById(R.id.teams)
        private val kickoff: TextView = itemView.findViewById(R.id.kickoff)
        private val btnHomeOdds: Button = itemView.findViewById(R.id.btn_home_odds)
        private val btnDrawOdds: Button = itemView.findViewById(R.id.btn_draw_odds)
        private val btnAwayOdds: Button = itemView.findViewById(R.id.btn_away_odds)

        fun bind(match: OddsData) {
            teams.text = "${match.homeTeam.displayName} vs ${match.awayTeam.displayName}"

            val formatter = DateTimeFormatter.ofPattern("EEE dd MMM 'at' HH:mm")
            kickoff.text = match.kickoffDate.format(formatter)

            homeBadge.setImageResource(match.homeTeam.badge)
            awayBadge.setImageResource(match.awayTeam.badge)

            btnHomeOdds.text = match.homeOddsFraction
            btnDrawOdds.text = match.drawOddsFraction
            btnAwayOdds.text = match.awayOddsFraction

            // Check for existing bet and update UI
            coroutineScope.launch {
                val existingBet = betRepository.getBet(match.matchId)
                updateButtonStates(existingBet)
            }

            // Set click listeners
            btnHomeOdds.setOnClickListener {
                handleBetClick(match, BetType.HOME_WIN, match.homeOdds)
            }

            btnDrawOdds.setOnClickListener {
                handleBetClick(match, BetType.DRAW, match.drawOdds)
            }

            btnAwayOdds.setOnClickListener {
                handleBetClick(match, BetType.AWAY_WIN, match.awayOdds)
            }
        }

        private fun handleBetClick(match: OddsData, betType: BetType, odds: Double) {
            coroutineScope.launch {
                val existingBet = betRepository.getBet(match.matchId)
                var newBetState: Bet? = null
                if (existingBet != null) {
                    betRepository.removeBet(match.matchId)
                } else {
                    newBetState = Bet(match.matchId, betType, odds)
                    betRepository.saveBet(newBetState)
                }

                updateButtonStates(newBetState)
            }
        }

        private fun updateButtonStates(bet: Bet?) {
            // Reset all buttons to default state
            resetButtonState(btnHomeOdds)
            resetButtonState(btnDrawOdds)
            resetButtonState(btnAwayOdds)

            // Highlight the selected bet if exists
            when (bet?.betType) {
                BetType.HOME_WIN -> {
                    highlightButton(btnHomeOdds)
                    hideButtons(btnDrawOdds, btnAwayOdds)
                }
                BetType.DRAW -> {
                    highlightButton(btnDrawOdds)
                    hideButtons(btnHomeOdds, btnAwayOdds)
                }
                BetType.AWAY_WIN -> {
                    highlightButton(btnAwayOdds)
                    hideButtons(btnHomeOdds, btnDrawOdds)
                }
                null -> { /* No bet selected */ }
            }
        }

        private fun resetButtonState(button: Button) {
            (button.parent as ViewGroup).visibility = View.VISIBLE
            button.setBackgroundResource(R.drawable.odds_button_background)
            button.setTextColor(getColor(itemView.context, android.R.color.white))
        }

        private fun highlightButton(button: Button) {
            button.setBackgroundResource(R.drawable.odds_button_selected_background)
            button.setTextColor(getColor(itemView.context, android.R.color.black))
        }

        private fun hideButtons(vararg buttons: Button) {
            for (button in buttons) {
                (button.parent as ViewGroup).visibility = View.INVISIBLE
            }
        }
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}