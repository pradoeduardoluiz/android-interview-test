package com.betsson.interviewtest.commom.presentation

import kotlinx.coroutines.flow.StateFlow

interface BetContract {
    interface ViewModel {
        val state: StateFlow<BetState>

        fun getBets()
    }
}
