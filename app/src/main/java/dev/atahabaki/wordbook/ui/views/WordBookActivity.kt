package dev.atahabaki.wordbook.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomNav()

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

    private fun initBottomNav() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.mainFramer)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.peekHeight = binding.wordsBottomAppbar.height
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        initBottomAppBarNavigationClick()
        dismissWhenClickToFramerListener()
    }

    private fun initBottomAppBarNavigationClick() {
        binding.wordsBottomAppbar.setNavigationOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun dismissWhenClickToFramerListener() {
        binding.mainFramer.setOnClickListener {
            dismissMainNavView()
        }
    }

    private fun dismissMainNavView() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun initView() {
        binding = ActivityWordbookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}