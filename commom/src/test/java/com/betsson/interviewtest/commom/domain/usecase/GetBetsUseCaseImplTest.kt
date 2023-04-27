package com.betsson.interviewtest.commom.domain.usecase

import com.betsson.interviewtest.commom.domain.model.BetModel
import com.betsson.interviewtest.commom.domain.repository.BetRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetBetsUseCaseImplTest {

    private val repository = mockk<BetRepository>()
    private lateinit var getBetsUseCase: GetBetsUseCase

    @Before
    @Test
    fun `test subject must not be null`() {
        getBetsUseCase = GetBetsUseCaseImpl(repository = repository)
        Assert.assertNotNull(getBetsUseCase)
    }

    @Test
    fun `should get bets from repository and order by sell in`() = runBlocking {

        val models = listOf(
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
        )

        val expected = listOf(
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc"),
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes")
        )

        coEvery { repository.getBets() } returns models

        val actual = getBetsUseCase()
        TestCase.assertEquals(actual, expected)

        coVerify(exactly = 1) { repository.getBets() }
        confirmVerified(repository)
    }

}
