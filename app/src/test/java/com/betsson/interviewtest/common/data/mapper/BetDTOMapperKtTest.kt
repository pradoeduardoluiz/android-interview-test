package com.betsson.interviewtest.common.data.mapper

import com.betsson.interviewtest.common.data.dto.BetDTO
import com.betsson.interviewtest.common.domain.model.BetModel
import org.junit.Assert.assertEquals
import org.junit.Test

class BetDTOMapperKtTest {
    @Test
    fun `should map bet dto to model`() {
        val original = BetDTO(
            type = "faucibus",
            sellIn = 3357,
            odds = 9447,
            image = "postulant"
        )
        val expected = BetModel(
            type = "faucibus",
            sellIn = 3357,
            odds = 9447,
            image = "postulant"
        )

        val actual = original.toModel()
        assertEquals(expected, actual)
    }
}
