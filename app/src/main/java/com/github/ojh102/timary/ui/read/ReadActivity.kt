package com.github.ojh102.timary.ui.read

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityReadBinding
import com.github.ojh102.timary.ui.complete.CompleteType
import com.github.ojh102.timary.util.Navigator
import com.github.ojh102.timary.util.TimaryParser
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ReadActivity : BaseActivity<ActivityReadBinding, ReadContract.ReadViewModel>() {

    override fun getLayoutRes() = R.layout.activity_read
    override fun getModelClass() = ReadContract.ReadViewModel::class.java

    @Inject protected lateinit var timaryParser: TimaryParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.inputs = viewModel
        binding.timaryParser = timaryParser

        setTimaryToolbar(binding.toolbar)

        bindObservable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_read, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_delete -> {
                viewModel.inputs.onClickDelete()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.capsule()
                        .subscribe(binding::setCapsule),

                viewModel.outputs.clickDelete()
                        .subscribeBy {
                            AlertDialog.Builder(this, R.style.TimaryDeleteAlertDialogStyle)
                                    .setTitle(getString(R.string.read_delete_title))
                                    .setMessage(getString(R.string.read_delete_message))
                                    .setPositiveButton(getString(R.string.delete), { _, _ ->
                                        viewModel.inputs.deleteCapsule()
                                    })
                                    .setNegativeButton(getString(R.string.cancel), null)
                                    .show()
                        },

                viewModel.outputs.completeDeleteCapsule()
                        .subscribeBy {
                            Navigator.navigateToCompleteActivity(this, CompleteType.DELETE, getString(R.string.format_delete_capsule_title, it))
                            finish()
                        }
        )
    }

}