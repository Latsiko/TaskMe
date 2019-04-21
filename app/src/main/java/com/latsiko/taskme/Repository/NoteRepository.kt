package com.latsiko.taskme.Repository

import androidx.lifecycle.LiveData
import com.latsiko.taskme.Database.AppDatabase
import com.latsiko.taskme.Database.NoteDto
import com.latsiko.taskme.Utilities.TaskMe

class NoteRepository {

    fun fetchAllNotes(): LiveData<List<NoteDto>> {
       return AppDatabase.invoke(TaskMe.instance).noteDao().getAllNotes()
    }

    fun persistNote(note: NoteDto) {
        AppDatabase.invoke((TaskMe.instance)).noteDao().insertNote(note)
    }

    fun deleteAll() {
        AppDatabase.invoke(TaskMe.instance).noteDao().deleteAllNotes()
    }

}