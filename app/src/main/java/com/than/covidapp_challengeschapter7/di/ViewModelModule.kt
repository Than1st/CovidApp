package com.than.covidapp_challengeschapter7.di

import com.than.covidapp_challengeschapter7.ui.detail.DetailFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.favorite.FavoriteFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeFragmentViewModel)
    viewModelOf(::DetailFragmentViewModel)
    viewModelOf(::FavoriteFragmentViewModel)
}