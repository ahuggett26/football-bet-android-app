package app.ahuggett.betsimapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.ahuggett.betsimapp.data.OddsData
import app.ahuggett.betsimapp.R
import java.time.format.DateTimeFormatter

class MatchAdapter(private val matches: List<OddsData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                MatchViewHolder(view)
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

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val homeBadge: ImageView = itemView.findViewById(R.id.home_badge)
        private val awayBadge: ImageView = itemView.findViewById(R.id.away_badge)
        private val teams: TextView = itemView.findViewById(R.id.teams)
        private val kickoff: TextView = itemView.findViewById(R.id.kickoff)
        private val btnHomeOdds: Button = itemView.findViewById(R.id.btn_home_odds)
        private val btnDrawOdds: Button = itemView.findViewById(R.id.btn_draw_odds)
        private val btnAwayOdds: Button = itemView.findViewById(R.id.btn_away_odds)

        fun bind(match: OddsData) {
            teams.text = "${match.homeTeam.displayName} vs ${match.awayTeam.displayName}"

            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' HH:mm")
            kickoff.text = match.kickoffDate.format(formatter)

            homeBadge.setImageResource(match.homeTeam.badge)
            awayBadge.setImageResource(match.awayTeam.badge)

            btnHomeOdds.text = match.homeOddsFraction
            btnDrawOdds.text = match.drawOddsFraction
            btnAwayOdds.text = match.awayOddsFraction
        }
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}