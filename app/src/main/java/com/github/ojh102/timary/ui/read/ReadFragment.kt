package com.github.ojh102.timary.ui.read

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentReadBinding
import javax.inject.Inject

internal class ReadFragment : BaseFragment<FragmentReadBinding>() {
    override val layoutRes = R.layout.fragment_read

    private val viewModel by viewModels<ReadViewModel> { viewModelFactory }

    private val args by navArgs<ReadFragmentArgs>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()

        viewModel.showDeleteDialog.observe(this, EventObserver {
            AlertDialog.Builder(context!!, R.style.TimaryDeleteAlertDialogStyle)
                .setTitle(getString(R.string.read_delete_title))
                .setMessage(getString(R.string.read_delete_message))
                .setPositiveButton(getString(R.string.delete)) { _, _ ->
                    viewModel.deleteCapsule(args.capsuleId)
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
                .show()
        })

        viewModel.navigateToComplete.observe(this, EventObserver { navController.navigate(it) })

        viewModel.loadCapusle(args.capsuleId)
    }

    private fun initToolbar() {
        binding.toolbar.run {
            inflateMenu(R.menu.menu_read)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_delete -> {
                        viewModel.onClickDelete()
                    }
                }

                false
            }

            setNavigationOnClickListener { navController.popBackStack() }
        }
    }
}
