package com.betsson.interviewtest.common.data.mapper

import com.betsson.interviewtest.common.data.dto.BetDTO
import com.betsson.interviewtest.common.domain.model.BetModel

fun BetDTO.toModel() = BetModel(
    type = type,
    sellIn = sellIn,
    odds = odds,
    image = image
)
