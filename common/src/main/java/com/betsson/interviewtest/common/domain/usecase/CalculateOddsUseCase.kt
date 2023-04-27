package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel

interface CalculateOddsUseCase {
    suspend operator fun invoke(bets: List<BetModel>): List<BetModel>
}
