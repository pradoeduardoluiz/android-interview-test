package com.betsson.interviewtest.commom.domain.model

data class BetModel(
    val type: String,
    var sellIn: Int,
    var odds: Int,
    val image: String
)
