package com.motonaka.googlebookssample.api

import com.motonaka.googlebookssample.response.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun fetchList(@Query("q") keyword: String): Response<SearchResponse>
}