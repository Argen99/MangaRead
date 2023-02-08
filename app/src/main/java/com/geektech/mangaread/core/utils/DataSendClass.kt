package com.geektech.mangaread.core.utils

import com.geektech.domain.model.SortByIssueYear

class DataSendClass {

    interface SearchBy {
        fun searchBy(text: String)

        fun getFilterData(types: List<String>?,genres: List<String>?,sortByIssueYear: SortByIssueYear?)
    }

    private var listener: SearchBy? = null

    fun setListener(listener: SearchBy) {
        this.listener = listener
    }

    fun search(searchText : String) {
        if(listener != null) {
            listener!!.searchBy(searchText)
        }
    }

    fun sendFilterData(types: List<String>?,genres: List<String>?, sortByIssueYear: SortByIssueYear?) {
        if(listener != null) {
            listener!!.getFilterData(types = types, genres = genres, sortByIssueYear)
        }
    }

    companion object {
        private var mInstance : DataSendClass? = null
        val instance : DataSendClass?
        get() {
            if(mInstance == null) {
                mInstance = DataSendClass()
            }
            return mInstance
        }
    }
}