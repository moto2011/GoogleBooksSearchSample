package com.motonaka.googlebookssample.response

data class SearchResponse(
        val kind: String,
        val totalItems: Int,
        val items: List<Volume>?
)