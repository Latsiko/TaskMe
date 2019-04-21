package com.latsiko.taskme.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.latsiko.taskme.Database.NoteDto
import com.latsiko.taskme.R
import com.latsiko.taskme.Repository.NoteRepository
import kotlinx.android.synthetic.main.activity_new_note.*
import java.util.concurrent.Executors

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)


        saveBtn.setOnClickListener {
            if (validateInputs()) {
                val noteDto = NoteDto(
                    noteLabel.text.toString().trim(),
                    noteDescription.text.toString().trim()
                )


                val executor = Executors.newSingleThreadExecutor()
                executor.execute {
                    val noteRepository = NoteRepository()
                    noteRepository.persistNote(noteDto)
                }

                Toast.makeText(this@NewNoteActivity, "Note created Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validateInputs(): Boolean {
        noteLabel.error = null
        noteDescription.error = null

        if (TextUtils.isEmpty(noteLabel.text.toString().trim())) {
            noteLabel.error = "Field required"
            noteLabel.requestFocus()
            return false
        }

        if (TextUtils.isEmpty(noteDescription.text.toString().trim())) {
            noteDescription.error = "Field required"
            noteDescription.requestFocus()
            return false
        }

        return true
    }
}
