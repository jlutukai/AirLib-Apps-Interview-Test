package com.airlibs.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val dispatcher: AirLibsDispatchers)

enum class AirLibsDispatchers {
    IO, MAIN, DEFAULT, UNCONFINED
}