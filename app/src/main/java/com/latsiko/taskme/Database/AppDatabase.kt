package com.latsiko.taskme.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Class that creates the Database Schema on the Mobile on app install
 */

/**
 * To Annotation Database diloni oti theloume na dimiourgithi mia vasi SQLite
 * me pinakes ta Entities (gia mas Note) kai diafora alla
 */
@Database(entities = [NoteDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile private var instance: AppDatabase? = null         // Is visible from all Threads not only Main thread
        private val LOCK = Any()                                    // Object that we need for synchronize
        private val DATABASE_NAME = "taskme.db"


        // Returns an DB instance with the Singleton Pattern
        // That way we ensure that we dont have more than one instance of the DB in our application
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                .build()
    }


    /**
     *  Applicable AppDatabase Dao
     */
    abstract fun noteDao(): NoteDao

}