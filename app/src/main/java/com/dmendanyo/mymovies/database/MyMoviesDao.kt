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

    @Query("SELECT * FROM MovieDB WHERE favorite = :isFavorite")
    fun getFavorites(isFavorite: Boolean = true): Flow<List<MovieDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieDB)

    @Query("SELECT * FROM MovieDB WHERE id = :id")
    fun findById(id: Int): Flow<MovieDB>

    @Query("SELECT COUNT(id) FROM MovieDB")
    suspend fun moviesCount(): Int
}
