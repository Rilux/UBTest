package com.example.unittest.presentation.list_page.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unittest.R
import com.example.unittest.databinding.FragmentCoinListBinding
import com.example.unittest.presentation.list_page.adapter.CoinsListAdapter
import com.example.unittest.presentation.list_page.model.CoinListFragmentUiState
import com.example.unittest.presentation.list_page.viewmodel.CoinListViewModel
import com.example.unittest.utils.onHide
import com.example.unittest.utils.onShow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding: FragmentCoinListBinding get() = _binding!!

    private val coinListViewModel by hiltNavGraphViewModels<CoinListViewModel>(R.id.nav_graph)

    private val adapter = CoinsListAdapter {
        coinListViewModel.selectCoin(it)

        val action =
            CoinListFragmentDirections.actionCoinListFragmentToCoinDetailsFragment()
        this.findNavController().navigate(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        coinListViewModel.loadCoinsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)

        setAdapter()
        setViewModelObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewListeners()
    }

    private fun setViewListeners() {
        binding.button.setOnClickListener {
            coinListViewModel.loadCoinsList()
        }
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setViewModelObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            coinListViewModel.coinListState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect { data ->
                    when (data) {
                        is CoinListFragmentUiState.ErrorOccurred -> {
                            binding.errorViewGroup.onShow()
                            binding.loadingViewGroup.onHide()
                            binding.successfullContentViewGroup.onHide()
                        }

                        is CoinListFragmentUiState.Loading -> {
                            binding.loadingViewGroup.onShow()
                            binding.errorViewGroup.onHide()
                            binding.successfullContentViewGroup.onHide()
                        }

                        is CoinListFragmentUiState.ListSuccessfullyFetched -> {
                            binding.successfullContentViewGroup.onShow()
                            binding.loadingViewGroup.onHide()
                            binding.errorViewGroup.onHide()
                            adapter.submitList(data.list)
                        }
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}