package com.betsson.interviewtest.commom.data.di

import com.betsson.interviewtest.commom.data.local.BetDataSourceMockImpl
import com.betsson.interviewtest.commom.data.repository.BetRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    factory { BetDataSourceMockImpl() }
    factory {
        BetRepositoryImpl(
            dataSource = get(),
            dispatcherIO = Dispatchers.IO
        )
    }
}

