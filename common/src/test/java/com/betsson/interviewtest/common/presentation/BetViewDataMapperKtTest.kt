package com.betsson.interviewtest.common.presentation

import com.betsson.interviewtest.common.domain.model.BetModel
import org.junit.Assert
import org.junit.Test

class BetViewDataMapperKtTest {
    @Test
    fun `should map bet model to view data`() {
        val original = BetModel(
            type = "faucibus",
            sellIn = 3357,
            odds = 9447,
            image = "postulant"
        )
        val expected = BetState.BetViewData(
            type = "faucibus",
            sellIn = 3357,
            odds = 9447,
            image = "postulant"
        )

        val actual = original.toViewData()
        Assert.assertEquals(expected, actual)
    }
}
