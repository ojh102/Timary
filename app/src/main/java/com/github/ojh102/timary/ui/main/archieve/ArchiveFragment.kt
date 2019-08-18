package com.github.ojh102.timary.ui.main.archieve

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.Navigator
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ArchiveFragment : BaseFragment<FragmentArchiveBinding, ArchiveContract.ArchiveViewModel>() {

    companion object {
        val TAG = "Archive"

        fun newInstance() = ArchiveFragment()
    }

    @Inject
    lateinit var archiveAdapter: ArchiveAdapter

    override fun getLayoutRes() = R.layout.fragment_archive

    override fun getModelClass() = ArchiveContract.ArchiveViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeRecyclerView()

        bindObservable()
    }

    private fun initializeRecyclerView() {
        binding.rvArchive.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = archiveAdapter
        }

        archiveAdapter.setCallbacks(object : ArchiveAdapter.Callbacks {
            override fun onClickArchiveCapsule(capsule: Capsule) {
                viewModel.inputs.onClickArchiveCapsule(capsule)
            }
        })
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.archiveCapsuleList()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy(
                                onNext = {
                                    binding.headerText = SpannableString(context?.getString(R.string.format_archive_header, it.size))
                                            .apply {
                                                setSpan(TextAppearanceSpan(context, R.style.B16Grape), 0, it.size.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                                            }

                                    archiveAdapter.submitList(it)
                                }
                        ),

                viewModel.outputs.navigateRead()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy(
                                onNext = { capsuleId ->
                                    context?.let {
                                        Navigator.navigateToReadActivity(it, capsuleId)
                                    }
                                }
                        )
        )
    }
}
