package com.betsson.interviewtest.commom.presentation.di

import com.betsson.interviewtest.commom.presentation.BetState
import com.betsson.interviewtest.commom.presentation.BetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    // ViewModel for BetViewModel
    viewModel {
        BetViewModel(
            getBetsUseCase = get(),
            initialState = BetState()
        )
    }

}
