package com.example.radiate

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class WriteJournal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_journal)

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title=""


        val journalTitle = findViewById<EditText>(R.id.titleToolbar)
        journalTitle.isFocusable = true

        journalTitle.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val newTitle = journalTitle.text.toString()
                supportActionBar?.title=newTitle
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.journal_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            R.id.shareJournal ->{
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.deleteJournal ->{
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.clearJournal ->{
                Toast.makeText(this,"Cleared",Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}