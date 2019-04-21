package com.motonaka.googlebookssample

import com.airbnb.epoxy.EpoxyController
import com.motonaka.googlebookssample.response.Volume

class SecondController : EpoxyController() {

    var valumes: List<Volume>? = null

    override fun buildModels() {
        if (valumes == null) {
            // TODO: Empty
            return
        }

        valumes?.let {
            it.forEach { volume ->
                itemVolume {
                    id(volume.id)
                    volume(volume.volumeInfo)
                }
            }
        }
    }
}