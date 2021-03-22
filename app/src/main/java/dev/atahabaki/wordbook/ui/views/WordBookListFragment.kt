package dev.atahabaki.wordbook.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.databinding.FragmentWordbookListBinding

class WordBookListFragment: Fragment(R.layout.fragment_wordbook_list) {
    private lateinit var _binding: FragmentWordbookListBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentWordbookListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.fragmentWordbookListRecyclerView.adapter = adapter
    }
}