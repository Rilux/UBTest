package com.example.unittest.presentation.list_page.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.domain.core.usecase.FetchCoinsListUseCase
import com.example.unittest.presentation.list_page.model.CoinListFragmentUiState
import com.example.unittest.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val fetchCoinsListUseCase: FetchCoinsListUseCase,
) : ViewModel() {

    private val _coinListState: MutableStateFlow<CoinListFragmentUiState> =
        MutableStateFlow(CoinListFragmentUiState.Loading)
    val coinListState: StateFlow<CoinListFragmentUiState> = _coinListState

    private val _selectedCoin: MutableStateFlow<CoinPresentation?> =
        MutableStateFlow(null)
    val selectedCoin: StateFlow<CoinPresentation?> = _selectedCoin

    fun loadCoinsList() {
        viewModelScope.launch {
            fetchCoinsListUseCase.execute()
                .onStart {
                    _coinListState.value = CoinListFragmentUiState.Loading
                }.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _coinListState.value = if (it.data != null) {
                                CoinListFragmentUiState.ListSuccessfullyFetched(it.data)
                            } else {
                                CoinListFragmentUiState.ErrorOccurred
                            }
                        }

                        Status.ERROR -> {
                            CoinListFragmentUiState.ErrorOccurred
                        }

                        Status.LOADING -> {
                            CoinListFragmentUiState.Loading
                        }
                    }
                }
        }
    }

    fun selectCoin(coin: CoinPresentation?) {
        _selectedCoin.value = coin
    }

}