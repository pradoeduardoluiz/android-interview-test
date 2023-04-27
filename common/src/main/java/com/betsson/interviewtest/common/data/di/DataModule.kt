package com.betsson.interviewtest.common.data.di

import com.betsson.interviewtest.common.data.local.BetDataSource
import com.betsson.interviewtest.common.data.local.BetDataSourceMockImpl
import com.betsson.interviewtest.common.data.repository.BetRepositoryImpl
import com.betsson.interviewtest.common.domain.repository.BetRepository
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

