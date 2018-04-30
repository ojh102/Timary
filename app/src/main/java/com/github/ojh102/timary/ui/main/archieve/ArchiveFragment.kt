package com.github.ojh102.timary.ui.main.archieve

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ArchiveFragment : BaseFragment<FragmentArchiveBinding, ArchiveContract.ArchiveViewModel>() {

    companion object {
        val TAG = "Archive"

        fun newInstance() = ArchiveFragment()
    }

    @Inject
    protected lateinit var archiveAdapter: ArchiveAdapter

    override fun getLayoutRes() = R.layout.fragment_archive

    override fun getModelClass() = ArchiveContract.ArchiveViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeRecyclerView()

        bindObservable()
    }

    private fun initializeRecyclerView() {
        binding.rvArchive.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = archiveAdapter
        }
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.archiveCapsuleList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onNext = {
                                    binding.headerText = SpannableString(context?.getString(R.string.format_archive_header, it.size))
                                            .apply {
                                                setSpan(TextAppearanceSpan(context, R.style.B16Grape), 0, it.size.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                                            }

                                    archiveAdapter.setItems(it)
                                }
                        )
        )
    }

}