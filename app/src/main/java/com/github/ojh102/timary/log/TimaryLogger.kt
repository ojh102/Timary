package com.github.ojh102.timary.log

import android.content.res.Resources
import com.amplitude.api.Amplitude
import com.github.ojh102.timary.R
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class TimaryLogger @Inject constructor(
        private val resources: Resources
) : TimaryLoggerApi {

    override fun btnTapHome() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnTapHome))
    }

    override fun btnTapArchive() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnTapArchive))
    }

    override fun btnTapSetting() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnTapSetting))
    }

    override fun btnOpened() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnOpened))
    }

    override fun btnClosed() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnClosed))
    }

    override fun btnWrite() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnWrite))
    }

    override fun btnStore() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnStore))
    }

    override fun btnCalendar() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnCalendar))
    }

    override fun btnNextSeason() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnNextSeason))
    }

    override fun btnLastDay() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnLastDay))
    }

    override fun btnFirstDay() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnFirstDay))
    }

    override fun btnRandom() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnRandom))
    }

    override fun btnComplete(text: String) {
        try {
            val lengthJson = JSONObject().apply {
                put("length", text)
            }

            Amplitude.getInstance().logEvent(resources.getString(R.string.btnComplete), lengthJson)
        } catch(e: Exception) {
            Amplitude.getInstance().logEvent(resources.getString(R.string.btnComplete) + " length : " + text.length)
        }
    }

    override fun btnArchived() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnArchived))
    }

    override fun btnDelete() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnDelete))
    }

    override fun btnConfirmCancel() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnConfirmCancel))
    }

    override fun btnConfirmDelete() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnConfirmDelete))
    }

    override fun btnAlert() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnAlert))
    }

    override fun btnTerms() {
        Amplitude.getInstance().logEvent(resources.getString(R.string.btnTerms))
    }
}