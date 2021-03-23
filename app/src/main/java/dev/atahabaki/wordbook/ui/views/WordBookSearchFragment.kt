package dev.atahabaki.wordbook.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.adapters.WordItemAdapter
import dev.atahabaki.wordbook.databinding.FragmentWordbookSearchBinding
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel
import javax.inject.Inject

@AndroidEntryPoint
class WordBookSearchFragment: Fragment(R.layout.fragment_wordbook_search) {
    private var _binding: FragmentWordbookSearchBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WordBookViewModelFactory

    private val viewModel: WordBookViewModel by viewModels {
        WordBookViewModel.provideFactory(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordbookSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WordItemAdapter(listOf())
        binding.fragmentWordbookSearchRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.fragmentWordbookSearchRecyclerView.adapter = adapter
        binding.wordbookSearchTextInput.editText?.doOnTextChanged { text, start, count, after ->
            activity?.let {
                viewModel.searchWords(text?.toString()!!.toLowerCase())?.observe(it, Observer {
                    adapter.items = it
                    adapter.notifyDataSetChanged()
                })
            }
            viewModel.searchWords(text.toString())
        }
        activity?.let {
            viewModel.getAllWords().observe(it, Observer{
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}