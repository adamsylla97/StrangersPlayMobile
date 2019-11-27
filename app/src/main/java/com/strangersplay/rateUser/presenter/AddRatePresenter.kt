package com.strangersplay.rateUser.presenter

import com.strangersplay.rateUser.network.RestAddRateService
import com.strangersplay.rateUser.view.AddRateView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddRatePresenter (addRateService: RestAddRateService,addRateView: AddRateView)
{
    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)




}