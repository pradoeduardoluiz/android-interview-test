package com.betsson.interviewtest.common.presentation

import kotlinx.coroutines.flow.StateFlow

interface BetContract {
    interface ViewModel {
        val state: StateFlow<BetState>

        fun getBets()
        fun calculateOdds()
    }
}
