package com.motonaka.googlebookssample.response

data class Volume(
    val kind: String,
    val id: String,
    val volumeInfo: VolumeInfo
)