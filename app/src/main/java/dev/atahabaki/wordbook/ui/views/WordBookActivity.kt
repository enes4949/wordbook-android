package dev.atahabaki.wordbook.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.adapters.WordItemAdapter
import dev.atahabaki.wordbook.data.databases.WordDatabase
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.data.repositories.WordRepository
import dev.atahabaki.wordbook.databinding.ActivityWordbookBinding
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel

class WordBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordbookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWordbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = WordDatabase(this)
        val repository = WordRepository(database)
        val factory = WordBookViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(WordBookViewModel::class.java)

        val adapter = WordItemAdapter(listOf(), viewModel)
        binding.wordsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.wordsRecyclerView.adapter = adapter

        viewModel.getAllWords().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.wordsFab.setOnClickListener {
            addJunkItem(viewModel)
        }
    }

   private fun addJunkItem(viewModel: WordBookViewModel) {
       viewModel.insertOrUpdate(WordItem("Merhaba", "Hi!"))
   }
}