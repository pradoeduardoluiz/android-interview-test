package com.betsson.interviewtest.commom.data.repository

import com.betsson.interviewtest.commom.data.local.BetDataSource
import com.betsson.interviewtest.commom.data.mapper.toModel
import com.betsson.interviewtest.commom.domain.model.BetModel
import com.betsson.interviewtest.commom.domain.repository.BetRepository
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


