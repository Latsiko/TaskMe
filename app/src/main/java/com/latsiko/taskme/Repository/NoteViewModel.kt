package com.latsiko.taskme.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latsiko.taskme.Database.NoteDto

class NoteViewModel: ViewModel() {


    private var noteRepository = NoteRepository()


    fun fetchAllNotes(): LiveData<List<NoteDto>> {

        return noteRepository.fetchAllNotes()
    }

}