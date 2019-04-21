package com.latsiko.taskme.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Data Access Object
 * Contains all the methods that we need in order to access the table in the Database
 *
 */

@Dao
interface NoteDao {

    /**
     * To Annotation Insert simeni oti i parakato sinartisi otan kalite
     * perni san orisma ena DTO k to kanei aftomata insert sti vasi
     */
    @Insert
    fun insertNote(note: NoteDto)

    /**
     * Antistixa to Annotation Delete
     */
    @Delete
    fun deleteNote(note: NoteDto)


    /**
     * To Annotation Query
     * den kanei kati sigkekrimeno apla tou dinis esi ena sql Query kai sto ektelei...
     */
    @Query("Delete from notes")
    fun deleteAllNotes()

    @Query("Select * from notes")
    fun getAllNotes(): LiveData<List<NoteDto>>

}