package com.thomas.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(shows: List<Show>)

    @Query("SELECT * FROM show_table")
    fun getAllShows(): LiveData<List<Show>>

    @Query("DELETE FROM show_table")
    fun deleteAll()
}
