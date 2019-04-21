package com.latsiko.taskme.RecyclerAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latsiko.taskme.R

class NoteViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var label: TextView = itemView.findViewById(R.id.note_label)
    internal var description: TextView = itemView.findViewById(R.id.note_description)

}