package com.betsson.interviewtest.common.presentation

import com.betsson.interviewtest.common.util.Event

data class BetState(
    val isLoading: Boolean = false,
    val bets: List<BetViewData> = emptyList(),
    val getBetsErrorEvent: Event.NoContent? = null,
    val calculateBetsErrorEvent: Event.NoContent? = null
) {
    data class BetViewData(
        val type: String,
        val sellIn: Int,
        val odds: Int,
        val image: String
    )
}

