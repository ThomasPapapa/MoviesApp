package com.thomas.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Show::class], version = 1, exportSchema = false
)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

}