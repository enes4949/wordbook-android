package dev.atahabaki.wordbook.ui.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.databinding.ActivityWordbookBinding
import dev.atahabaki.wordbook.ui.viewmodelfactories.WordBookViewModelFactory
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel
import dev.atahabaki.wordbook.utils.Constants
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
        navigateToList()
        viewModel.fabState.observe(this, Observer {
            if (it) binding.wordsFab.visibility = View.GONE
            else binding.wordsFab.visibility = View.VISIBLE
        })

        binding.wordsFab.setOnClickListener {
            addJunkItem(viewModel)
        }

        handleNavMenu()

        binding.wordsBottomAppbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.words_menu_search -> {
                    navigateToSearch()
                    true
                }
                else -> false
            }
        }
    }

    private fun handleNavMenu() {
        binding.wordbookNavView.setNavigationItemSelectedListener {
            dismissMainNavView()
            when(it.itemId) {
                R.id.words_nav_menu_coffee -> {
                    gotoCoffee()
                    true
                }
                R.id.words_nav_menu_feedback -> {
                    gotoFeedback()
                    true
                }
                else -> false
            }
        }
    }

    private fun gotoCoffee() = openInBrowser(Constants.BUYMEACOFFE_LINK)
    private fun gotoFeedback() = openInBrowser(Constants.FEEDBACK_LINK)

    private fun openInBrowser(link: String) {
       val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }

    private fun navigateToList() = navigateToWhere(WordBookListFragment())
    private fun navigateToSearch() = navigateToWhere(WordBookSearchFragment())

    private fun navigateToWhere(where: Fragment) {
        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.activity_wordbook_framer, where)
            it.commit()
        }
    }

   private fun addJunkItem(viewModel: WordBookViewModel) {
       val junks = listOf<WordItem>(
           WordItem("Merhaba", "Hi"),
           WordItem("Wow", "Shit"),
           WordItem("Cool", "Incredible"),
           WordItem("Rechercher", "ara")
       )
       val random = (junks.indices).random()
       viewModel.insertOrUpdate(junks[random])
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