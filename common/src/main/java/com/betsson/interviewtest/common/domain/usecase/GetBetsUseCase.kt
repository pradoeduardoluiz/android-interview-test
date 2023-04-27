package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel

interface GetBetsUseCase {
    suspend operator fun invoke(): List<BetModel>
}
