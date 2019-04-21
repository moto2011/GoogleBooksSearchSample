package com.motonaka.googlebookssample

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motonaka.googlebookssample.repository.GoogleBooksRepository
import com.motonaka.googlebookssample.response.SearchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondViewModel(private val repository: GoogleBooksRepository): ViewModel() {

    val state: MutableLiveData<Boolean> = MutableLiveData()
    val item: MutableLiveData<SearchResponse> = MutableLiveData()

    var keyword: String? = null

    fun search() {
        GlobalScope.launch {
            state.postValue(true)
            keyword?.let {
                val result = repository.search(keyword = it)
                if (result.isSuccessful) {
                    item.postValue(result.body())
                } else {
                }
            }
        }
    }
}