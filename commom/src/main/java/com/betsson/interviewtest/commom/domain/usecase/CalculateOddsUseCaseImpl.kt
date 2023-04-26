package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel

class CalculateOddsUseCaseImpl : CalculateOddsUseCase {
    override suspend fun invoke(bets: List<BetModel>): List<BetModel> {
        for (i in bets.indices) {
            if (bets[i].type != "Total score" && bets[i].type != "Number of fouls") {
                if (bets[i].odds > 0) {
                    if (bets[i].type != "First goal scorer") {
                        bets[i].odds = bets[i].odds - 1
                    }
                }
            } else {
                if (bets[i].odds < 50) {
                    bets[i].odds = bets[i].odds + 1

                    if (bets[i].type == "Number of fouls") {
                        if (bets[i].sellIn < 11) {
                            if (bets[i].odds < 50) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }

                        if (bets[i].sellIn < 6) {
                            if (bets[i].odds < 50) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }
                    }
                }
            }

            if (bets[i].type != "First goal scorer") {
                bets[i].sellIn = bets[i].sellIn - 1
            }

            if (bets[i].sellIn < 0) {
                if (bets[i].type != "Total score") {
                    if (bets[i].type != "Number of fouls") {
                        if (bets[i].odds > 0) {
                            if (bets[i].type != "First goal scorer") {
                                bets[i].odds = bets[i].odds - 1
                            }
                        }
                    } else {
                        bets[i].odds = bets[i].odds - bets[i].odds
                    }
                } else {
                    if (bets[i].odds < 50) {
                        bets[i].odds = bets[i].odds + 1
                    }
                }
            }
        }

        return bets
    }
}
