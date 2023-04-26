package com.betsson.interviewtest.commom.data.repository

import com.betsson.interviewtest.commom.data.local.BetDataSource
import com.betsson.interviewtest.commom.data.mapper.toModel
import com.betsson.interviewtest.commom.domain.model.BetModel
import com.betsson.interviewtest.commom.domain.repository.BetRepository

class BetRepositoryImpl(private val dataSource: BetDataSource) : BetRepository {
    override fun getBets(): List<BetModel> {
        return dataSource.getBets().map { bet -> bet.toModel() }
    }
}


