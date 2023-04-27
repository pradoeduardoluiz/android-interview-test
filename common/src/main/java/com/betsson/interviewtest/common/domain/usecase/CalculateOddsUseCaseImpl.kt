package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel
import kotlinx.coroutines.delay

class CalculateOddsUseCaseImpl : CalculateOddsUseCase {
    override suspend fun invoke(bets: List<BetModel>): List<BetModel> {
        delay(LOADING_DELAY) // simulate network delay
        val updatedBets = bets.map { bet ->
            calculateOddsAndSellIn(bet = bet)
        }
        return updatedBets.sortedBy { it.sellIn }
    }

    private fun calculateOddsAndSellIn(bet: BetModel): BetModel {
        var sellIn = bet.sellIn
        var odds = bet.odds
        odds = calculateOdds(bet, odds, sellIn)
        sellIn = decrementSellInWhenTypeIsNotFirstGoalScorer(bet, sellIn)
        odds = calculateWhenSellInLessThanZero(sellIn, bet, odds)
        return bet.copy(sellIn = sellIn, odds = odds)
    }

    private fun calculateOdds(
        bet: BetModel,
        odds: Int,
        sellIn: Int
    ): Int {
        var updateOdds = odds
        if (bet.type != TOTAL_SCORE && bet.type != NUMBER_OF_FOULS) {
            if (updateOdds > ODDS_ZERO && bet.type != FIRST_GOAL_SCORER) {
                updateOdds -= 1
            }
        } else {
            if (updateOdds < ODDS_FIFTY) {
                updateOdds += 1
                updateOdds = calculateWhenTypeIsNumberOfFouls(bet, sellIn, updateOdds)
            }
        }
        return updateOdds
    }

    private fun calculateWhenTypeIsNumberOfFouls(
        bet: BetModel,
        sellIn: Int,
        odds: Int
    ): Int {
        var updateOdds = odds
        if (bet.type == NUMBER_OF_FOULS && updateOdds < ODDS_FIFTY) {
            if (sellIn < SELL_IN_ELEVEN) {
                updateOdds += 1
            }

            if (sellIn < SELL_IN_SIX) {
                updateOdds += 1
            }
        }
        return updateOdds
    }

    private fun decrementSellInWhenTypeIsNotFirstGoalScorer(
        bet: BetModel,
        sellIn: Int
    ): Int {
        var updateSellIn = sellIn
        if (bet.type != FIRST_GOAL_SCORER) {
            updateSellIn -= 1
        }
        return updateSellIn
    }

    private fun calculateWhenSellInLessThanZero(
        sellIn: Int,
        bet: BetModel,
        odds: Int
    ): Int {
        var updateOdds = odds
        if (sellIn < SELL_IN_ZERO) {
            if (bet.type != TOTAL_SCORE) {
                updateOdds = calculateWhenTypeIsNotNumberOfFouls(bet, updateOdds)
            } else {
                if (updateOdds < ODDS_FIFTY) {
                    updateOdds += 1
                }
            }
        }
        return updateOdds
    }

    private fun calculateWhenTypeIsNotNumberOfFouls(
        bet: BetModel,
        odds: Int
    ): Int {
        var odds1 = odds
        if (bet.type != NUMBER_OF_FOULS) {
            if (odds1 > ODDS_ZERO && bet.type != FIRST_GOAL_SCORER) {
                odds1 -= 1
            }
        } else {
            odds1 = 0
        }
        return odds1
    }

    private companion object {
        const val TOTAL_SCORE = "Total score"
        const val NUMBER_OF_FOULS = "Number of fouls"
        const val FIRST_GOAL_SCORER = "First goal scorer"
        const val ODDS_ZERO = 0
        const val ODDS_FIFTY = 50
        const val SELL_IN_ZERO = 0
        const val SELL_IN_SIX = 6
        const val SELL_IN_ELEVEN = 11
        const val LOADING_DELAY = 600L
    }
}
