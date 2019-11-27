package com.strangersplay.rateUser.presenter

import android.util.Log
import com.strangersplay.Config
import com.strangersplay.rateUser.model.Rate
import com.strangersplay.rateUser.network.RestAddRateService
import com.strangersplay.rateUser.view.AddRateView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddRatePresenter (private val addRateService: RestAddRateService,addRateView: AddRateView){

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun sendRates(first: Rate) {
        ioScope.launch {
            val response = addRateService.addRate(Config.username, first)
            Log.i("supertest123",response.toString())
        }
    }

}