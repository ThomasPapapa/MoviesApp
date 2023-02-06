package com.thomas.data.di

import androidx.room.Room
import com.thomas.data.client.APIClient
import com.thomas.data.datasource.local.ShowDatabase
import com.thomas.data.datasource.remote.ShowsDatasource
import com.thomas.data.datasource.remote.ShowsDatasourceImpl
import com.thomas.data.repositories.ShowsRepository
import com.thomas.data.repositories.ShowsRepositoryImpl
import com.thomas.data.services.TVMazeAPI
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { APIClient(androidContext()) }
    single<TVMazeAPI> { get<APIClient>().retrofit.create(TVMazeAPI::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            ShowDatabase::class.java,
            "ShowDatabase"
        ).build()
    }

    single {
        val database = get<ShowDatabase>()
        database.showDao()
    }

    single<ShowsRepository> { ShowsRepositoryImpl(get(), get()) }
    single<ShowsDatasource> { ShowsDatasourceImpl(get()) }
}