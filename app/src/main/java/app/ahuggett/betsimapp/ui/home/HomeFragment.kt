package app.ahuggett.betsimapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.ahuggett.betsimapp.data.bet.BetDatabase
import app.ahuggett.betsimapp.data.bet.BetRepository
import app.ahuggett.betsimapp.databinding.FragmentHomeBinding
import app.ahuggett.betsimapp.data.exampleOdds

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var matchAdapter: MatchAdapter
    private lateinit var betRepository: BetRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initBetRepository()
        setupRecyclerView()
        return root
    }

    private fun initBetRepository() {
        val database = BetDatabase.getDatabase(requireContext())
        betRepository = BetRepository(database.betDao())
    }

    private fun setupRecyclerView() {
        matchAdapter = MatchAdapter(exampleOdds, betRepository, lifecycleScope)
        binding.rvMatches.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}