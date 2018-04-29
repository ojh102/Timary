package com.github.ojh102.timary.ui.write.store

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityStoreBinding
import com.github.ojh102.timary.ui.complete.CompleteType
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.toast
import com.github.ojh102.timary.util.intent.Navigator
import com.github.ojh102.timary.util.rx.IOTransfer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_store.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class StoreActivity : BaseActivity<ActivityStoreBinding, StoreContract.StoreViewModel>() {

    override fun getLayoutRes() = R.layout.activity_store
    override fun getModelClass() = StoreContract.StoreViewModel::class.java

    @Inject
    protected lateinit var storeAdapter: StoreAdapter

    @Inject
    protected lateinit var timaryParser: TimaryParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.inputs = viewModel
        binding.timaryParser = timaryParser

        storeAdapter.callbacks = object : StoreAdapter.Callbacks {
            override fun onItemSelect(item: StoreItem, position: Int) {
                viewModel.inputs.onClickStoreItem(item, position)
            }
        }

        setTimaryToolbar(binding.toolbar)
        initializeRecyclerView()
        bindObservable()
    }

    private fun initializeRecyclerView() {
        rv_store.layoutManager = LinearLayoutManager(this)
        rv_store.adapter = storeAdapter
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.storeDate()
                        .subscribe {
                            storeAdapter.setItems(it)
                        },

                viewModel.outputs.clickStoreItem()
                        .subscribe {
                            if (it.second == 0) {
                                showDatePickerDialog()
                            } else {
                                binding.storeItem = it.first
                            }
                        },

                viewModel.outputs.completeStoreCapsule()
                        .compose(IOTransfer())
                        .subscribeBy(onNext = {
                            Navigator.navigateToCompleteActivity(
                                    context = this,
                                    type = CompleteType.WRITE,
                                    title = timaryParser.completeWriteText(it.targetDate),
                                    isClear = true
                            )
                        }, onError = {
                            Timber.e(it)
                            toast(it.message)
                        })
        )
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }

        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->

            val selectedCal = Calendar.getInstance().apply {
                set(year, month, day)
            }

            binding.storeItem = StoreItem("달력에서 선택한 ", selectedCal.timeInMillis)

        }, cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH]).apply {
            setCancelable(false)
            setButton(DatePickerDialog.BUTTON_NEGATIVE, null, { _, _ -> })
            datePicker.minDate = cal.timeInMillis
        }

        dialog.show()
    }

}