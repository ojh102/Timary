package com.github.ojh102.timary.ui.write.content

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityWriteBinding
import com.github.ojh102.timary.util.Navigator
import com.github.ojh102.timary.util.TimaryParser
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.withLatestFrom
import java.util.*
import javax.inject.Inject

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteContract.WriteViewModel>() {

    override fun getLayoutRes() = R.layout.activity_write
    override fun getModelClass() = WriteContract.WriteViewModel::class.java

    @Inject
    protected lateinit var timaryParser: TimaryParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.inputs = viewModel
        binding.today = timaryParser.dateToTitleWithLine(Date().time)

        setTimaryToolbar(binding.toolbar)

        bindObservable()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_write, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {

            R.id.menu_write -> {
                viewModel.inputs.onClickWrite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.clickWrite()
                        .withLatestFrom(viewModel.outputs.outputContent())
                        .map { it.second }
                        .filter { it.isNotBlank() }
                        .subscribeBy {
                            Navigator.navigateToStoreActivity(this, it)
                        }
        )
    }

}
