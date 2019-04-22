package com.motonaka.googlebookssample.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.motonaka.googlebookssample.repository.GoogleBooksRepository
import com.motonaka.googlebookssample.response.SearchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class SecondViewModel(private val repository: GoogleBooksRepository) : ViewModel() {

    val state: MutableLiveData<Boolean> = MutableLiveData()
    val item: MutableLiveData<SearchResponse> = MutableLiveData()
    val error: MutableLiveData<Boolean> = MutableLiveData()

    fun search(keyword: String?) {
        GlobalScope.launch {
            state.postValue(false)
            keyword?.let {
                try {
                    val result = repository.search(keyword = it)
                    if (result.isSuccessful) {
                        item.postValue(result.body())
                    }
                } catch (e: IOException) {
                    error.postValue(true)
                }
            }
            state.postValue(true)
        }
    }
}