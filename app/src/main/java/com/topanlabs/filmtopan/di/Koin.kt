package com.topanlabs.filmtopan.di

import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.detail.DetailViewModel
import com.topanlabs.filmtopan.list.ListViewModel
import com.topanlabs.filmtopan.network.RetroBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {
    val appModule = module {

        single<DataRepository> { DataRepository(get()) }
        single { RetroBuilder.tmApi }
        viewModel { ListViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}