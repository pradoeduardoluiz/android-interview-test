package com.betsson.interviewtest.commom.domain.repository

import com.betsson.interviewtest.commom.domain.model.BetModel

interface BetRepository {
    suspend fun getBets(): List<BetModel>
}
