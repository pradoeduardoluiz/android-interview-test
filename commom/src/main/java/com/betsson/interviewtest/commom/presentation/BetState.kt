package com.betsson.interviewtest.commom.presentation

data class BetState(
    val isLoading: Boolean = false,
    val bets: List<BetViewData> = emptyList()
) {
    data class BetViewData(
        var type: String,
        var sellIn: Int,
        var odds: Int,
        var image: String
    )
}
