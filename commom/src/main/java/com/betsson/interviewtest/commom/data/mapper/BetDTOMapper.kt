package com.betsson.interviewtest.commom.data.mapper

import com.betsson.interviewtest.commom.data.dto.BetDTO
import com.betsson.interviewtest.commom.domain.model.BetModel

fun BetDTO.toModel() = BetModel(
    type = type,
    sellIn = sellIn,
    odds = odds,
    image = image
)
