package com.betsson.interviewtest.commom.presentation

data class BetState(
    val isLoading: Boolean = false,
    val bets: List<BetViewData> = emptyList()
) {
    data class BetViewData(
        val type: String,
        val sellIn: Int,
        val odds: Int,
        val image: String
    )
}
