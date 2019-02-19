/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    private val STRING_TO_BE_TYPED = "Hello Espresso"
    private val inputText = onView(withId(R.id.editTextUserInput))
    private val perform = onView(withId(R.id.changeTextBt))
    private val checkText = onView(withId(R.id.textToBeChanged))
    private val activityChange = onView(withId(R.id.activityChangeTextBtn))
    private val showTextView = onView(withId(R.id.show_text_view))

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun changeText_sameActivity() {
        inputText.perform(typeText(STRING_TO_BE_TYPED))
        perform.perform(click())
        checkText.check(matches(withText(STRING_TO_BE_TYPED)))
    }

    @Test
    fun changeText_newActivity() {
        inputText.perform(typeText(STRING_TO_BE_TYPED))
        activityChange.perform(click())
        showTextView.check(matches(withText(STRING_TO_BE_TYPED)))
    }
}
