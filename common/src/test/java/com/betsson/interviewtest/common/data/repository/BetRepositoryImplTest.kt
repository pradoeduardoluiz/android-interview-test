package com.betsson.interviewtest.common.data.repository

import com.betsson.interviewtest.common.data.dto.BetDTO
import com.betsson.interviewtest.common.data.local.BetDataSource
import com.betsson.interviewtest.common.domain.model.BetModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BetRepositoryImplTest {

    private val dataSource = mockk<BetDataSource>()
    private val dispatcherTest = StandardTestDispatcher()
    private lateinit var betRepository: BetRepositoryImpl

    @Before
    @Test
    fun `test subject must not be null`() {
        betRepository = BetRepositoryImpl(dataSource = dataSource, dispatcherIO = dispatcherTest)
        Assert.assertNotNull(betRepository)
    }

    @Test
    fun `should get bets from data source`() = runTest(dispatcherTest) {
        val dtos = listOf(
            BetDTO(type = "mei", sellIn = 2586, odds = 3343, image = "nunc"),
            BetDTO(type = "ne", sellIn = 5627, odds = 5547, image = "montes")
        )

        val models = listOf(
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc"),
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes")
        )

        coEvery { dataSource.getBets() } returns dtos

        val actual = betRepository.getBets()
        assertEquals(actual, models)

        coVerify(exactly = 1) { dataSource.getBets() }
        confirmVerified(dataSource)
    }

}
