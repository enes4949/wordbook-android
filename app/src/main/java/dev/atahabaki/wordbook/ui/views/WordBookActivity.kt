package dev.atahabaki.wordbook.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.databinding.ActivityWordbookBinding
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel
import javax.inject.Inject

@AndroidEntryPoint
class WordBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordbookBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    @Inject
    lateinit var viewModelFactory: WordBookViewModelFactory

    private val viewModel: WordBookViewModel by viewModels {
        WordBookViewModel.provideFactory(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomNav()

        binding.wordsFab.setOnClickListener {
            addJunkItem(viewModel)
        }

        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.activity_wordbook_framer, WordBookListFragment())
            it.commit()
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