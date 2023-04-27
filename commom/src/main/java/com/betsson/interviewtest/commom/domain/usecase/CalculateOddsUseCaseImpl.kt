package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel

class CalculateOddsUseCaseImpl : CalculateOddsUseCase {
    override suspend fun invoke(bets: List<BetModel>): List<BetModel> {
        val updatedBets = bets.map { bet ->
            calculateSingleBet(bet = bet)
        }
        return updatedBets.sortedBy { it.sellIn }
    }

    private fun calculateSingleBet(bet: BetModel): BetModel {
        var sellIn = bet.sellIn
        var odds = bet.odds

        if (bet.type != TOTAL_SCORE && bet.type != NUMBER_OF_FOULS) {
            if (odds > ODDS_ZERO) {
                if (bet.type != FIRST_GOAL_SCORER) {
                    odds -= 1
                }
            }
        } else {
            if (odds < ODDS_FIFTY) {
                odds += 1

                if (bet.type == NUMBER_OF_FOULS) {
                    if (sellIn < SELL_IN_ELEVEN) {
                        if (odds < ODDS_FIFTY) {
                            odds += 1
                        }
                    }

                    if (sellIn < SELL_IN_SIX) {
                        if (odds < ODDS_FIFTY) {
                            odds += 1
                        }
                    }
                }
            }
        }

        if (bet.type != FIRST_GOAL_SCORER) {
            sellIn -= 1
        }

        if (sellIn < SELL_IN_ZERO) {
            if (bet.type != TOTAL_SCORE) {
                if (bet.type != NUMBER_OF_FOULS) {
                    if (odds > ODDS_ZERO) {
                        if (bet.type != FIRST_GOAL_SCORER) {
                            odds -= 1
                        }
                    }
                } else {
                    odds = 0
                }
            } else {
                if (odds < ODDS_FIFTY) {
                    odds += 1
                }
            }
        }

        return bet.copy(sellIn = sellIn, odds = odds)
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
    }
}
