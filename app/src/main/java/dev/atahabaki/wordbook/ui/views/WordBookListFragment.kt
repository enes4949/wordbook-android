package dev.atahabaki.wordbook.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.adapters.WordItemAdapter
import dev.atahabaki.wordbook.databinding.FragmentWordbookListBinding
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel
import javax.inject.Inject

@AndroidEntryPoint
class WordBookListFragment: Fragment(R.layout.fragment_wordbook_list) {
    private var _binding: FragmentWordbookListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WordBookViewModelFactory

    private val viewModel: WordBookViewModel by viewModels {
        WordBookViewModel.provideFactory(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordbookListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WordItemAdapter(listOf())
        binding.fragmentWordbookListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.fragmentWordbookListRecyclerView.adapter = adapter

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.delete(adapter.getWordAtPosition(viewHolder.adapterPosition))
                adapter.notifyDataSetChanged()
            }
        }).attachToRecyclerView(binding.fragmentWordbookListRecyclerView)

        activity?.let {
            viewModel.getAllWords().observe(it, Observer{
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}