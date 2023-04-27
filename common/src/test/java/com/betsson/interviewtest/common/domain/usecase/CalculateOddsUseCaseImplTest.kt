package com.betsson.interviewtest.common.domain.usecase

import com.betsson.interviewtest.common.domain.model.BetModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculateOddsUseCaseImplTest {

    private lateinit var calculateOddsUseCase: CalculateOddsUseCase

    @Before
    @Test
    fun `test subject must not be null`() {
        calculateOddsUseCase = CalculateOddsUseCaseImpl()
        Assert.assertNotNull(calculateOddsUseCase)
    }

    @Test
    fun `should calculate bet when type is Total score and odds less than fifty and sell bigger than zero`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = 2,
                    odds = 0,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = 1,
                    odds = 1,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is Total score and odds bigger than fifty and sell bigger than zero`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = 2,
                    odds = 50,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = 1,
                    odds = 50,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is Number of Fouls and odds less than fifty and sell in bigger than zero`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = 5,
                    odds = 47,
                    image = "https://i.imgur.com/NMLpcKj.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = 4,
                    odds = 50,
                    image = "https://i.imgur.com/NMLpcKj.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is Number of Fouls and odds bigger than fifty and sell in less than eleven`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = 5,
                    odds = 49,
                    image = "https://i.imgur.com/NMLpcKj.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = 4,
                    odds = 50,
                    image = "https://i.imgur.com/NMLpcKj.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is not Total Score and Number of Fouls and odds bigger than zero`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Winning team",
                    sellIn = 10,
                    odds = 20,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Winning team",
                    sellIn = 9,
                    odds = 19,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is not Total Score and sell in is negative`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Winning team",
                    sellIn = -1,
                    odds = 20,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Winning team",
                    sellIn = -2,
                    odds = 18,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is Number of fouls and sell in is negative`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = -1,
                    odds = 20,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Number of fouls",
                    sellIn = -2,
                    odds = 0,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bet when type is Total Score and sell in is negative`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = -1,
                    odds = 10,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "Total score",
                    sellIn = -2,
                    odds = 12,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                )
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

    @Test
    fun `should calculate bets and sort by sell in`() =
        runBlocking {

            val bets = listOf(
                BetModel(
                    type = "Winning team",
                    sellIn = 10,
                    odds = 20,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                ),
                BetModel(
                    type = "Total score",
                    sellIn = 2,
                    odds = 0,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                ),
                BetModel(
                    type = "Player performance",
                    sellIn = 5,
                    odds = 7,
                    image = "https://i.imgur.com/Urpc00H.jpeg"
                ),
                BetModel(
                    type = "First goal scorer",
                    sellIn = 0,
                    odds = 80,
                    image = "https://i.imgur.com/Wy94Tt7.jpeg"
                )
            )

            val expected = listOf(
                BetModel(
                    type = "First goal scorer",
                    sellIn = 0,
                    odds = 80,
                    image = "https://i.imgur.com/Wy94Tt7.jpeg"
                ),
                BetModel(
                    type = "Total score",
                    sellIn = 1,
                    odds = 1,
                    image = "https://i.imgur.com/VnPRqcv.jpeg"
                ),
                BetModel(
                    type = "Player performance",
                    sellIn = 4,
                    odds = 6,
                    image = "https://i.imgur.com/Urpc00H.jpeg"
                ),
                BetModel(
                    type = "Winning team",
                    sellIn = 9,
                    odds = 19,
                    image = "https://i.imgur.com/mx66SBD.jpeg"
                ),
            )

            val actual = calculateOddsUseCase(bets)
            Assert.assertEquals(actual, expected)
        }

}
