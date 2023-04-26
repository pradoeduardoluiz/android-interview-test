package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel

class CalculateOddsUseCaseImpl : CalculateOddsUseCase {
    override suspend fun invoke(bets: List<BetModel>): List<BetModel> {
        for (i in bets.indices) {
            if (bets[i].type != TOTAL_SCORE && bets[i].type != NUMBER_OF_FOULS) {
                if (bets[i].odds > ODDS_ZERO) {
                    if (bets[i].type != FIRST_GOAL_SCORER) {
                        bets[i].odds = bets[i].odds - 1
                    }
                }
            } else {
                if (bets[i].odds < ODDS_FIFTY) {
                    bets[i].odds = bets[i].odds + 1

                    if (bets[i].type == NUMBER_OF_FOULS) {
                        if (bets[i].sellIn < SELL_IN_ELEVEN) {
                            if (bets[i].odds < ODDS_FIFTY) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }

                        if (bets[i].sellIn < SELL_IN_SIX) {
                            if (bets[i].odds < ODDS_FIFTY) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }
                    }
                }
            }

            if (bets[i].type != FIRST_GOAL_SCORER) {
                bets[i].sellIn = bets[i].sellIn - 1
            }

            if (bets[i].sellIn < SELL_IN_ZERO) {
                if (bets[i].type != TOTAL_SCORE) {
                    if (bets[i].type != NUMBER_OF_FOULS) {
                        if (bets[i].odds > ODDS_ZERO) {
                            if (bets[i].type != FIRST_GOAL_SCORER) {
                                bets[i].odds = bets[i].odds - 1
                            }
                        }
                    } else {
                        bets[i].odds = bets[i].odds - bets[i].odds
                    }
                } else {
                    if (bets[i].odds < ODDS_FIFTY) {
                        bets[i].odds = bets[i].odds + 1
                    }
                }
            }
        }

        return bets.sortedBy { it.sellIn }
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
