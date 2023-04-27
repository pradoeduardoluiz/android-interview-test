package com.betsson.interviewtest.common.presentation

import com.betsson.interviewtest.common.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.common.domain.usecase.GetBetsUseCase
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
