package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel

interface CalculateOddsUseCase {
    suspend operator fun invoke(bets: List<BetModel>): List<BetModel>
}
