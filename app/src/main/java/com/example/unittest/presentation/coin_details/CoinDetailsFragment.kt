package com.example.unittest.presentation.coin_details

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.unittest.R
import com.example.unittest.databinding.FragmentCoinDetailsBinding
import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.presentation.list_page.viewmodel.CoinListViewModel
import com.example.unittest.utils.onHide
import com.example.unittest.utils.onShow
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentCoinDetailsBinding? = null
    private val binding: FragmentCoinDetailsBinding get() = _binding!!

    private val coinListViewModel by hiltNavGraphViewModels<CoinListViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            coinListViewModel.selectedCoin
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { data ->
                    if(data != null) {
                        setView(data)
                    } else {
                        parentFragmentManager.popBackStack()
                    }
                }
        }
    }

    private fun setView(coin: CoinPresentation) {
        binding.coinPriceTextView.text = coin.price
        binding.coinTitleTextView.text = coin.name

        GlideToVectorYou
            .init()
            .with(context).setPlaceHolder(R.drawable.ic_dollar, R.drawable.ic_dollar)
            .load(Uri.parse(coin.iconUrl), binding.coinImageView)

        binding.contenGroup.onShow()
        binding.loadingGroup.onHide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}