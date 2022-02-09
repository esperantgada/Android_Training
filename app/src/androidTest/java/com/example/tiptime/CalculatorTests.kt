package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    //Lunch the activity specified by the developper
    @get : Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

   @Test
    fun calculate_default_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))

       onView(withId(R.id.calculate_button))
           .perform(click())

       onView(withId(R.id.tip_result))
           .check(matches(withText(containsString("20.00"))))

   }

    @Test
    fun calculate_18_percent_tips(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))

        onView(withId(R.id.eighteen))
            .perform(click())

        onView(withId(R.id.eighteen))
            .check(matches(isChecked()))

        onView(withId(R.id.twenty))
            .check(matches(isNotChecked()))

        onView(withId(R.id.fifteen))
            .check(matches(isNotChecked()))

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("18.00"))))
    }

    @Test
    fun calculate_15_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))

        onView(withId(R.id.fifteen))
            .perform(click())

        onView(withId(R.id.fifteen))
            .check(matches(isChecked()))

        onView(withId(R.id.twenty))
            .check(matches(isNotChecked()))

        onView(withId(R.id.eighteen))
            .check(matches(isNotChecked()))

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("15.00"))))
    }

    @Test
    fun round_up_result(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("556.00"))

        onView(withId(R.id.fifteen))
            .perform(click())

        onView(withId(R.id.fifteen))
            .check(matches(isChecked()))

        onView(withId(R.id.twenty))
            .check(matches(isNotChecked()))

        onView(withId(R.id.eighteen))
            .check(matches(isNotChecked()))

        onView(withId(R.id.round_up_switch))
            .perform(click())

        onView(withId(R.id.round_up_switch))
            .check(matches(isNotChecked()))

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("83.40"))))
    }
}