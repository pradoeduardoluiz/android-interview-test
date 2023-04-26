package com.betsson.interviewtest.commom.data.local

import com.betsson.interviewtest.commom.data.dto.BetDTO

interface BetDataSource {
    fun getBets(): List<BetDTO>
}
