package com.betsson.interviewtest.common.domain.di

import com.betsson.interviewtest.common.domain.usecase.CalculateOddsUseCase
import com.betsson.interviewtest.common.domain.usecase.CalculateOddsUseCaseImpl
import com.betsson.interviewtest.common.domain.usecase.GetBetsUseCase
import com.betsson.interviewtest.common.domain.usecase.GetBetsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetBetsUseCase> {
        GetBetsUseCaseImpl(
            repository = get()
        )
    }
    factory<CalculateOddsUseCase> {
        CalculateOddsUseCaseImpl()
    }
}

