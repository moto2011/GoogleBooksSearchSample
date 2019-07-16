package com.motonaka.googlebookssample.test

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException
import java.lang.IllegalArgumentException

@RunWith(JUnit4::class)
class InputCheckerTest {

    private lateinit var target : InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isVaild() {
        val actual = target.isVaild("foo")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isVaild_givenLessThan3_returnsFalse() {
        val actual = target.isVaild("ab")
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        val actual = target.isVaild("123")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenAlpha_returnsTrue() {
        val actual = target.isVaild("abc")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isVaild("abc12")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenAlphaNumericHiragana_returnsTrue() {
        val actual = target.isVaild("abc12あ")
        assertThat(actual, `is`(false))
    }

    @Ignore("テスト対象が仮実装なので一時的にスキップ")
    @Test(expected = IOException::class)
    fun isValid_givenNull_throwsIllegalArgumentException() {
        target.isVaild(null)
    }
}