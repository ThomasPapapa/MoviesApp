package com.thomas.data.datasource.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "show_table")
@TypeConverters(GenreTypeConverter::class)
data class Show(
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
    val name: String? = null,
    val language: String? = null,
    val genres: List<String>,
    val runtime: Int? = 0,
    val imageMedium: String? = null,
    val imageOriginal: String? = null,
    val ratingAverage: Double? = null,
    val summary: String? = null

)
