package com.github.ojh102.timary.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.github.ojh102.timary.R
import com.github.ojh102.timary.util.KEY_TEXT_CONTENT
import com.github.ojh102.timary.util.KEY_TEXT_TITLE
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val title = intent.getStringExtra(KEY_TEXT_TITLE)
        val content = intent.getStringExtra(KEY_TEXT_CONTENT)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        tv_content.text = content
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}