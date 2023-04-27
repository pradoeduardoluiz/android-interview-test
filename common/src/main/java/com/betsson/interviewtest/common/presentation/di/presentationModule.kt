package com.betsson.interviewtest.common.presentation.di

import com.betsson.interviewtest.common.presentation.BetState
import com.betsson.interviewtest.common.presentation.BetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    // ViewModel for BetViewModel
    viewModel {
        BetViewModel(
            getBetsUseCase = get(),
            calculateOddsUseCase = get(),
            initialState = BetState()
        )
    }

}
