package com.latsiko.taskme.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data Transfer Object
 * Represents a class that matches with the Table in our Database
 */

@Entity(tableName = "notes")
class NoteDto {

    constructor(noteLabel: String?, noteDescription: String?) {
        this.noteLabel = noteLabel
        this.noteDescription = noteDescription
    }

    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0

    @ColumnInfo(name = "label")
    var noteLabel: String?

    @ColumnInfo(name = "description")
    var noteDescription: String?
}
