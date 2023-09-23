package com.dmendanyo.mymovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyMoviesDao {

    @Query("SELECT * FROM MovieDB")
    fun getAllMovies(): Flow<List<MovieDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDB>)
}
