package com.example.unittest.presentation.list_page.model

import com.example.unittest.domain.core.model.CoinPresentation

sealed class CoinListFragmentUiState {
    object ErrorOccurred : CoinListFragmentUiState()

    object Loading : CoinListFragmentUiState()

    data class ListSuccessfullyFetched(
        val list: List<CoinPresentation>,
    ) : CoinListFragmentUiState()
}
