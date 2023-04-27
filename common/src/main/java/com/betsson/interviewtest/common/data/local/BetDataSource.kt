package com.betsson.interviewtest.common.data.local

import com.betsson.interviewtest.common.data.dto.BetDTO

interface BetDataSource {
    suspend fun getBets(): List<BetDTO>
}
