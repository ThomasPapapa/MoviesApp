package com.thomas.data.di

import com.thomas.data.client.APIClient
import com.thomas.data.datasource.shows.ShowsDatasource
import com.thomas.data.datasource.shows.ShowsDatasourceImpl
import com.thomas.data.services.TVMazeAPI
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { APIClient(androidContext()) }
    single<TVMazeAPI> { get<APIClient>().retrofit.create(TVMazeAPI::class.java) }
    single<ShowsDatasource> { ShowsDatasourceImpl(get()) }
}