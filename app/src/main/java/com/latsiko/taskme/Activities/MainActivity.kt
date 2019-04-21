package com.latsiko.taskme.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latsiko.taskme.Database.NoteDto
import com.latsiko.taskme.R
import com.latsiko.taskme.RecyclerAdapter.NoteRecyclerAdapter
import com.latsiko.taskme.Repository.NoteRepository
import com.latsiko.taskme.Repository.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import androidx.recyclerview.widget.ItemTouchHelper


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var items: MutableList<NoteDto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = NoteRecyclerAdapter(this@MainActivity, items)

        recyclerView = findViewById<RecyclerView>(com.latsiko.taskme.R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        newNoteBtn.setOnClickListener {
            startActivity(Intent(this, NewNoteActivity::class.java))
        }

        val model = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        model.fetchAllNotes().observe(this, Observer {
            (viewAdapter as NoteRecyclerAdapter).refreshDataSet(it ?: emptyList())
            viewAdapter.notifyDataSetChanged()

        })

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                Toast.makeText(this@MainActivity, "on Swiped ", Toast.LENGTH_SHORT).show()
                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.adapterPosition
                items.removeAt(position)
                viewAdapter.notifyDataSetChanged()
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.latsiko.taskme.R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.latsiko.taskme.R.id.delete_all_notes -> {
                deleteAllNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            val noteRepository = NoteRepository()
            noteRepository.deleteAll()
        }
    }
}
