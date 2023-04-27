package com.betsson.interviewtest.common.domain.repository

import com.betsson.interviewtest.common.domain.model.BetModel

interface BetRepository {
    suspend fun getBets(): List<BetModel>
}
