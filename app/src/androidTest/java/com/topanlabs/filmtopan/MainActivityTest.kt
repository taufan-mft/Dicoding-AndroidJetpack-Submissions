package com.topanlabs.filmtopan

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.topanlabs.filmtopan.data.FilmFactory
import com.topanlabs.filmtopan.data.TvFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val films = FilmFactory.listData
    private val tvs = TvFactory.listData

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadFilms() {
        onView(withId(R.id.recView)).check(matches(isDisplayed()))
        onView(withId(R.id.recView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                films.size
            )
        )
    }

    @Test
    fun loadDetailFilm() {
        onView(withId(R.id.recView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(films[0].name)))
        onView(withId(R.id.tvOverview)).check(matches(withText(films[0].shortDesc)))
    }

    @Test
    fun loadTvs() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.recView)).check(matches(isDisplayed()))
        onView(withId(R.id.recView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvs.size
            )
        )
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.recView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(tvs[0].name)))
    }
}