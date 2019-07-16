package com.motonaka.googlebookssample.test

import java.lang.IllegalArgumentException

class InputChecker {
    fun isVaild(text: String?) :Boolean {
        if (text == null) throw IllegalArgumentException("cannot be null")
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))
    }
}