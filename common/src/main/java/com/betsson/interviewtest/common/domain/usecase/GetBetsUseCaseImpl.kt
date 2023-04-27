package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel
import com.betsson.interviewtest.common.domain.repository.BetRepository

class GetBetsUseCaseImpl(
    private val repository: BetRepository
) : GetBetsUseCase {
    override suspend fun invoke(): List<BetModel> {
        return repository.getBets().sortedBy { it.sellIn }
    }
}
