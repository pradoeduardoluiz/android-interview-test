package com.betsson.interviewtest.commom.domain.di

import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCase
import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetBetsUseCase> {
        GetBetsUseCaseImpl(
            repository = get()
        )
    }
}

