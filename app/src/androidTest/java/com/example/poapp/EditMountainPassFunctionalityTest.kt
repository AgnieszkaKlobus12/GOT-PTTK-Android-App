package com.example.poapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.poapp.view.MainActivity
import com.example.poapp.view.member.MountainPassAdapter
import org.junit.Rule
import org.junit.Test


class EditMountainPassFunctionalityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun basicRoute() {
        onView(withId(R.id.mountainPass)).check(matches(isDisplayed()))
        onView(withId(R.id.mountainPass)).perform(click())
        onView(withId(R.id.mountain_passes_list)).perform(RecyclerViewActions.actionOnItemAtPosition<MountainPassAdapter.MountainPassOfficialItemHolder>(0, click()))
    }

}