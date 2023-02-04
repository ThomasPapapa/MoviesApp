package com.thomas.moviesapp.di

import android.app.Application
import com.thomas.moviesapp.viewmodels.showlist.ShowListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ShowListViewModel(get(), (androidContext() as Application)) }
}