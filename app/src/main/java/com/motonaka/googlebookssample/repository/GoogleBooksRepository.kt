package com.motonaka.googlebookssample.repository

import com.motonaka.googlebookssample.api.GoogleBooksApi
import com.motonaka.googlebookssample.response.SearchResponse
import retrofit2.Response

interface GoogleBooksRepository {

    suspend fun search(keyword: String): Response<SearchResponse>
}

class GoogleBooksRepositoryImpl(val api: GoogleBooksApi): GoogleBooksRepository {

    override suspend fun search(keyword: String): Response<SearchResponse> {
        return api.fetchList(keyword).await()
    }
}