package com.betsson.interviewtest.commom.presentation

import com.betsson.interviewtest.commom.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCase
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BetViewModelTest {

    private val getBetsUseCase = mockk<GetBetsUseCase>()
    private val calculateOddsUseCase = mockk<CalculateOddsUseCase>()
    private lateinit var viewModel: BetContract.ViewModel

    @Before
    @Test
    fun `test subject must not be null`() {
        viewModel = BetViewModel(
            getBetsUseCase = getBetsUseCase,
            calculateOddsUseCase = calculateOddsUseCase,
            initialState = BetState()
        )
        Assert.assertNotNull(getBetsUseCase)
    }

}
