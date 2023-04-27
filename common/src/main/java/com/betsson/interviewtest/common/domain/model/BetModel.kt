package com.betsson.interviewtest.common.domain.model

data class BetModel(
    val type: String,
    var sellIn: Int,
    var odds: Int,
    val image: String
)
