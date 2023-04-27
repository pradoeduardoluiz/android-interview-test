package com.betsson.interviewtest.commom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.interviewtest.commom.domain.model.BetModel
import com.betsson.interviewtest.commom.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BetViewModel(
    private val getBetsUseCase: GetBetsUseCase,
    private val calculateOddsUseCase: CalculateOddsUseCase,
    initialState: BetState
) : ViewModel(), BetContract.ViewModel {
    private val mutableState = MutableStateFlow(initialState)
    override val state: StateFlow<BetState> = mutableState

    private var bets: List<BetModel> = listOf()

    override fun getBets() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            delay(LOADING_DELAY)
            runCatching { getBetsUseCase() }
                .onSuccess { bets ->
                    this@BetViewModel.bets = bets
                    updateState {
                        copy(
                            isLoading = false,
                            bets = bets.map { bet -> bet.toViewData() }
                        )
                    }
                }
                .onFailure {
                    updateState {
                        copy(isLoading = false)
                    }
                }
        }
    }

    override fun calculateOdds() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            delay(LOADING_DELAY)
            runCatching { calculateOddsUseCase(bets = this@BetViewModel.bets) }
                .onSuccess { updatedBets ->
                    this@BetViewModel.bets = updatedBets
                    updateState {
                        copy(
                            isLoading = false,
                            bets = updatedBets.map { bet -> bet.toViewData() }
                        )
                    }
                }
                .onFailure {
                    updateState {
                        copy(isLoading = false)
                    }
                }
        }
    }

    private suspend fun updateState(handler: suspend BetState.() -> BetState) {
        mutableState.value = handler(mutableState.value)
    }

    private companion object {
        const val LOADING_DELAY = 600L
    }
}
