package com.betsson.interviewtest.common.presentation

import com.betsson.interviewtest.common.domain.model.BetModel

fun BetModel.toViewData() = BetState.BetViewData(
    type = type,
    sellIn = sellIn,
    odds = odds,
    image = image
)
