package com.motonaka.googlebookssample.presentation

import com.airbnb.epoxy.EpoxyController
import com.motonaka.googlebookssample.itemEmpty
import com.motonaka.googlebookssample.itemVolume
import com.motonaka.googlebookssample.response.Volume

class SecondController : EpoxyController() {

    var valumes: List<Volume>? = null

    override fun buildModels() {
        if (valumes == null || valumes?.isEmpty() == true) {
            itemEmpty {
                id("empty")
            }
            return
        }

        valumes?.let {
            it.forEach { volume ->
                itemVolume {
                    id(volume.id)
                    volumeInfo(volume.volumeInfo)
                }
            }
        }
    }
}