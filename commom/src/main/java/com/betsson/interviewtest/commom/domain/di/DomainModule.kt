package com.betsson.interviewtest.commom.domain.di

import com.betsson.interviewtest.commom.domain.usecase.GetBetsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetBetsUseCaseImpl(
            repository = get()
        )
    }
}

