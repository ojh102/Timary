package com.github.ojh102.timary.log

interface TimaryLoggerApi {
    fun btnTapHome()
    fun btnTapArchive()
    fun btnTapSetting()

    fun btnOpened()
    fun btnClosed()

    fun btnWrite()
    fun btnStore()

    fun btnCalendar()
    fun btnNextSeason()
    fun btnLastDay()
    fun btnFirstDay()
    fun btnRandom()
    fun btnComplete(text: String)

    fun btnArchived()
    fun btnDelete()
    fun btnConfirmCancel()
    fun btnConfirmDelete()

    fun btnAlert()
    fun btnTerms()
}