package com.latsiko.taskme.RecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.latsiko.taskme.Database.NoteDto
import com.latsiko.taskme.R

class NoteRecyclerAdapter(private val activityContext: Context, list: List<NoteDto>): RecyclerView.Adapter<NoteViewHolder>() {

    var noteList:  List<NoteDto> = emptyList()

    init {
        noteList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(activityContext)
                .inflate(R.layout.note_item, parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.label.text = noteList[position].noteLabel
        holder.description.text = noteList[position].noteDescription
    }

    fun refreshDataSet(list: List<NoteDto>) {
        noteList = list
    }
}