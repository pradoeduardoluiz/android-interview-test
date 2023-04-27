package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel
import com.betsson.interviewtest.common.domain.repository.BetRepository
import kotlinx.coroutines.delay

class GetBetsUseCaseImpl(
    private val repository: BetRepository
) : GetBetsUseCase {
    override suspend fun invoke(): List<BetModel> {
        delay(LOADING_DELAY) // simulate network delay
        return repository.getBets().sortedBy { it.sellIn }
    }

    private companion object {
        const val LOADING_DELAY = 600L
    }
}
