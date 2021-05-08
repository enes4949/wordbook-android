package dev.atahabaki.wordbook.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.databinding.FragmentWordbookAddBinding
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel

@AndroidEntryPoint
class WordBookAddFragment: Fragment(R.layout.fragment_wordbook_add) {
	private var _binding: FragmentWordbookAddBinding? = null
	private val binding get() = _binding!!

	private val viewModel: WordBookViewModel by viewModels()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentWordbookAddBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
