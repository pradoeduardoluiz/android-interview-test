package com.betsson.interviewtest.commom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BetViewModel(
    private val getBetsUseCase: GetBetsUseCase,
    private val initialState: BetState
) : ViewModel(), BetContract.ViewModel {
    private val mutableState = MutableStateFlow(initialState)
    override val state: StateFlow<BetState> = mutableState

    override fun getBets() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            runCatching { getBetsUseCase() }
                .onSuccess { bets ->
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

    private suspend fun updateState(handler: suspend BetState.() -> BetState) {
        mutableState.value = handler(mutableState.value)
    }
}
