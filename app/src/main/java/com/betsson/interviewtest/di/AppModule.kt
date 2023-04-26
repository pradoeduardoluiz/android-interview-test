package com.betsson.interviewtest.di

import com.betsson.interviewtest.view.list.BetAdapter
import org.koin.dsl.module

val appModules = module {
    factory { BetAdapter() }
}
