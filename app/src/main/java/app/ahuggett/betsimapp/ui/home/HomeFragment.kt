package app.ahuggett.betsimapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.ahuggett.betsimapp.databinding.FragmentHomeBinding
import app.ahuggett.betsimapp.data.exampleOdds

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        return root
    }

    private fun setupRecyclerView() {
        matchAdapter = MatchAdapter(exampleOdds)
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