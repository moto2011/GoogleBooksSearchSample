package com.motonaka.googlebookssample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motonaka.googlebookssample.repository.GoogleBooksRepository

class MainViewModel(private val repository: GoogleBooksRepository) : ViewModel() {

//    val item = MutableLiveData<String>()
//
//    init {
//        item.observeForever {
//            repository.search("")
//        }
//    }
}