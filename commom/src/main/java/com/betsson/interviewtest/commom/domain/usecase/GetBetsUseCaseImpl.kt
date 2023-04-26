package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel
import com.betsson.interviewtest.commom.domain.repository.BetRepository

class GetBetsUseCaseImpl(
    private val repository: BetRepository
) : GetBetsUseCase {
    override suspend fun invoke(): List<BetModel> {
        return repository.getBets()
    }
}
