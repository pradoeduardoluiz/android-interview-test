package com.betsson.interviewtest.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.betsson.interviewtest.R
import com.betsson.interviewtest.common.presentation.BetContract
import com.betsson.interviewtest.common.presentation.BetViewModel
import com.betsson.interviewtest.common.util.handleEvent
import com.betsson.interviewtest.databinding.FragmentBetsBinding
import com.betsson.interviewtest.extensions.isShimmering
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BetsFragment : Fragment(R.layout.fragment_bets) {

    private val viewModel: BetContract.ViewModel by viewModel<BetViewModel>()
    private val adapter by inject<BetAdapter>()

    private var _binding: FragmentBetsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBets()
        bindAdapter()
        bindOutputs()
        bindInputs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindInputs() = with(binding) {
        swipeRefresh.setOnRefreshListener {
            viewModel.getBets()
            swipeRefresh.isRefreshing = false
        }
        calculate.setOnClickListener {
            viewModel.calculateOdds()
        }
    }

    private fun bindAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun bindOutputs() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.apply {
                    recyclerView.isVisible = !state.isLoading
                    shimmer.isShimmering = state.isLoading
                    adapter.submitList(state.bets)
                    state.getBetsErrorEvent.handleEvent {
                        showSnackBarError(msg = getString(R.string.error_get_bets))
                    }
                    state.calculateBetsErrorEvent.handleEvent {
                        showSnackBarError(msg = getString(R.string.error_calculate_odds))
                    }
                }
            }
        }
    }

    private fun showSnackBarError(
        msg: String
    ) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

}
