package com.motonaka.googlebookssample.response

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val imageLinks: ImageLinks,
    val mainCategory: String
) {
    fun getAuthorsStr() = if (authors != null) authors.joinToString(",") else ""
}