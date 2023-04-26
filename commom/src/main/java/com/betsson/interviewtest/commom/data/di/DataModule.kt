package com.betsson.interviewtest.commom.data.di

import com.betsson.interviewtest.commom.data.local.BetDataSource
import com.betsson.interviewtest.commom.data.local.BetDataSourceMockImpl
import com.betsson.interviewtest.commom.data.repository.BetRepositoryImpl
import com.betsson.interviewtest.commom.domain.repository.BetRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    factory<BetDataSource> { BetDataSourceMockImpl() }
    factory<BetRepository> {
        BetRepositoryImpl(
            dataSource = get(),
            dispatcherIO = Dispatchers.IO
        )
    }
}

