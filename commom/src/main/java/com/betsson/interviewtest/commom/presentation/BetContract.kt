package com.betsson.interviewtest.commom.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface BetContract {
    interface ViewModel {
        val state: StateFlow<BetState>

        fun getBets()
    }
}

interface State<T> {

    val mutableState: MutableStateFlow<T>

    suspend fun updateState(handler: suspend T.() -> T)
}
