package dev.atahabaki.wordbook.ui.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dev.atahabaki.wordbook.R
import dev.atahabaki.wordbook.data.entities.WordItem
import dev.atahabaki.wordbook.databinding.ActivityWordbookBinding
import dev.atahabaki.wordbook.ui.viewmodels.FabStateViewModel
import dev.atahabaki.wordbook.ui.viewmodels.SabStateViewModel
import dev.atahabaki.wordbook.ui.viewmodels.WordBookViewModel
import dev.atahabaki.wordbook.utils.Constants

@AndroidEntryPoint
class WordBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordbookBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private val viewModel: WordBookViewModel by viewModels()

    private val fabViewModel: FabStateViewModel by viewModels()
    private val sabViewModel: SabStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomNav()
        navigateToList()
        binding.wordsFab.setOnClickListener {
            navigateToAdd()
        }

        handleNavMenu()

        fabViewModel.fabState.observe(this, {
            if (it) {
                binding.wordsFab.hide()
            }
            else {
                binding.wordsFab.show()
            }
        })

        binding.wordsBottomAppbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.words_menu_search -> {
                    sabViewModel.selectSabState(false)
                    true
                }
                R.id.words_menu_delete_all -> {
                    showDeleteConfirmation().show()
                    true
                }
                else -> false
            }
        }
    }

    private fun showDeleteConfirmation(): AlertDialog {
        return MaterialAlertDialogBuilder(this).setTitle(R.string.are_you_sure)
            .setMessage(getString(R.string.confirmation_delete_all))
            .setPositiveButton(R.string.ok) { dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
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

    private fun navigateToList() = navigateToWhere(WordBookListFragment(), false)
    private fun navigateToAdd() = navigateToWhere(WordBookAddFragment())

    private fun navigateToWhere(where: Fragment, addToStack: Boolean = true) {
        supportFragmentManager.beginTransaction().also {
            if (addToStack) it.replace(R.id.activity_wordbook_framer, where).addToBackStack(where.tag)
            else it.replace(R.id.activity_wordbook_framer, where)
            it.commit()
        }
    }

   private fun addJunkItem(viewModel: WordBookViewModel) {
       val junks = listOf(
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