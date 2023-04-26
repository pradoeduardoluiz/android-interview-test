package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel

interface GetBetsUseCase {
    suspend operator fun invoke(): List<BetModel>
}
