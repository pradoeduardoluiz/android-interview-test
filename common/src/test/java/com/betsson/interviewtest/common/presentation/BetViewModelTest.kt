package com.betsson.interviewtest.common.presentation

import com.betsson.interviewtest.common.domain.model.BetModel
import com.betsson.interviewtest.common.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.common.domain.usecase.GetBetsUseCase
import com.betsson.interviewtest.common.util.Event
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BetViewModelTest {

    private val getBetsUseCase = mockk<GetBetsUseCase>()
    private val calculateOddsUseCase = mockk<CalculateOddsUseCase>()
    private val dispatcherTest = Dispatchers.Unconfined
    private lateinit var viewModel: BetContract.ViewModel

    @Before
    @Test
    fun `test subject must not be null`() {
        Dispatchers.setMain(dispatcherTest)
        viewModel = BetViewModel(
            getBetsUseCase = getBetsUseCase,
            calculateOddsUseCase = calculateOddsUseCase,
            initialState = BetState()
        )
        Assert.assertNotNull(getBetsUseCase)
    }

    @Test
    fun `should get bets`() = runBlocking {
        val stateValues = arrayListOf<BetState>()
        val stateJob = launch(dispatcherTest) {
            viewModel.state.toList(stateValues)
        }

        val models = listOf(
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
        )

        val expectedState = BetState(
            isLoading = false, bets = listOf(
                BetState.BetViewData(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
                BetState.BetViewData(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
            )
        )

        coEvery { getBetsUseCase() } returns models

        viewModel.getBets()

        val firstState = stateValues.first()
        Assert.assertEquals(firstState, BetState())

        val lastState = stateValues.last()
        Assert.assertEquals(expectedState, lastState)

        stateJob.cancel()

        coVerify(exactly = 1) { getBetsUseCase() }
        confirmVerified(getBetsUseCase)
    }

    @Test
    fun `should get bets and throw Exception`() = runBlocking {
        val stateValues = arrayListOf<BetState>()
        val stateJob = launch(dispatcherTest) {
            viewModel.state.toList(stateValues)
        }

        val expectedState = BetState(
            isLoading = false,
            getBetsErrorEvent = Event.NoContent.create()
        )

        coEvery { getBetsUseCase() } throws Exception()

        viewModel.getBets()

        val firstState = stateValues.first()
        Assert.assertEquals(firstState, BetState())

        val lastState = stateValues.last()
        Assert.assertEquals(
            expectedState.getBetsErrorEvent?.value,
            lastState.getBetsErrorEvent?.value
        )

        stateJob.cancel()

        coVerify(exactly = 1) { getBetsUseCase() }
        confirmVerified(getBetsUseCase)
    }

    @Test
    fun `should calculate bets`() = runBlocking {
        val stateValues = arrayListOf<BetState>()
        val stateJob = launch(dispatcherTest) {
            viewModel.state.toList(stateValues)
        }

        val models = listOf(
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
        )

        val expectedState = BetState(
            isLoading = false, bets = listOf(
                BetState.BetViewData(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
                BetState.BetViewData(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
            )
        )

        coEvery { getBetsUseCase() } returns models
        coEvery { calculateOddsUseCase(models) } returns models

        viewModel.getBets()
        viewModel.calculateOdds()

        val firstState = stateValues.first()
        Assert.assertEquals(firstState, BetState())

        val lastState = stateValues.last()
        Assert.assertEquals(expectedState, lastState)

        stateJob.cancel()

        coVerify(exactly = 1) {
            getBetsUseCase()
            calculateOddsUseCase(models)
        }
        confirmVerified(getBetsUseCase, calculateOddsUseCase)
    }

    @Test
    fun `should calculate bets and throw Exception`() = runBlocking {
        val stateValues = arrayListOf<BetState>()
        val stateJob = launch(dispatcherTest) {
            viewModel.state.toList(stateValues)
        }

        val models = listOf(
            BetModel(type = "ne", sellIn = 5627, odds = 5547, image = "montes"),
            BetModel(type = "mei", sellIn = 2586, odds = 3343, image = "nunc")
        )

        val expectedState = BetState(
            isLoading = false,
            calculateBetsErrorEvent = Event.NoContent.create()
        )

        coEvery { getBetsUseCase() } returns models
        coEvery { calculateOddsUseCase(models) } throws Exception()

        viewModel.getBets()
        viewModel.calculateOdds()

        val firstState = stateValues.first()
        Assert.assertEquals(firstState, BetState())

        val lastState = stateValues.last()
        Assert.assertEquals(
            expectedState.calculateBetsErrorEvent?.value,
            lastState.calculateBetsErrorEvent?.value
        )

        stateJob.cancel()

        coVerify(exactly = 1) {
            getBetsUseCase()
            calculateOddsUseCase(models)
        }
        confirmVerified(getBetsUseCase, calculateOddsUseCase)
    }

}
