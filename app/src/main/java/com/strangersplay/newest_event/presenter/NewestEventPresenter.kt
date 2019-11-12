package com.strangersplay.newest_event.presenter

import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.newest_event.view.NewestEventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewestEventPresenter(
    private val newestEventService: NewestEventService,
    private val newestEventView: NewestEventView
) {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun displayNewestEvents() {

        ioScope.launch {
            val events = newestEventService.getNewestItems()
            mainScope.launch {
                newestEventView.updateList(events)
            }
        }

    }

    fun filterList(filterOptions: FilterOptions) {
        ioScope.launch {
            val events = newestEventService.getNewestItems()
            mainScope.launch {
                when (filterOptions) {
                    FilterOptions.NEARLEST -> {
                        newestEventView.updateList(events.sortedBy {
                            it.eventLocation
                        })
                    }
                    FilterOptions.MOREPEOPLE -> {
                        newestEventView.updateList(events)
                    }
                    FilterOptions.LESSPEOPLE -> {
                        newestEventView.updateList(events)
                    }
                    FilterOptions.HIGHERPRICE -> {
                        newestEventView.updateList(events.sortedBy {
                            it.price
                        })
                    }
                    FilterOptions.LOWERPRICE -> {
                        newestEventView.updateList(events.sortedByDescending {
                            it.price
                        })
                    }
                }
            }
        }

    }
}