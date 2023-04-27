package com.betsson.interviewtest.common.data.repository

import com.betsson.interviewtest.common.data.local.BetDataSource
import com.betsson.interviewtest.common.data.mapper.toModel
import com.betsson.interviewtest.common.domain.model.BetModel
import com.betsson.interviewtest.common.domain.repository.BetRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BetRepositoryImpl(
    private val dataSource: BetDataSource,
    private val dispatcherIO: CoroutineDispatcher
) : BetRepository {
    override suspend fun getBets(): List<BetModel> {
        return withContext(dispatcherIO) {
            dataSource.getBets().map { bet -> bet.toModel() }
        }
    }
}


