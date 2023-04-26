package com.betsson.interviewtest.commom.data.local

import com.betsson.interviewtest.commom.data.dto.BetDTO

interface BetDataSource {
    suspend fun getBets(): List<BetDTO>
}
