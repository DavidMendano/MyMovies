package com.dmendanyo.mymovies.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDB::class], version = 1, exportSchema = false)
abstract class MyMoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): MyMoviesDao
}
