package com.betsson.interviewtest.commom.presentation

import com.betsson.interviewtest.commom.domain.model.BetModel

fun BetModel.toViewData() = BetState.BetViewData(
    type = type,
    sellIn = sellIn,
    odds = odds,
    image = image
)
