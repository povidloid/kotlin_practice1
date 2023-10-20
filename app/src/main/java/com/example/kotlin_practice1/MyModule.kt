package com.example.kotlin_practice1

import org.koin.dsl.module

val myModule = module {
    single {MyViewModel()}
}