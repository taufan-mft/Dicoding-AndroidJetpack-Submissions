package com.topanlabs.filmtopan

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.TmHead
import com.topanlabs.filmtopan.data.TmTvHead
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {
    val repository by inject<DataRepository>()
    val idlingResource by inject<EspressoIdlingResource>()
    lateinit var films: TmHead
    lateinit var tvs: TmTvHead

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        runBlocking {
            films = repository.getFilms()
            tvs = repository.getTvs()
        }
        IdlingRegistry.getInstance().register(idlingResource.idlingResource)
    }

    @Test
    fun loadFilms() {

        onView(withId(R.id.recView)).check(matches(isDisplayed()))
        onView(withId(R.id.recView)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        films.totalResults
                )
        )
    }

    @Test
    fun loadTvs() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.recView)).check(matches(isDisplayed()))
        onView(withId(R.id.recView)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        tvs.totalResults
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
        onView(withId(R.id.tvTitle)).check(matches(withText(films.results[0].originalTitle)))
        onView(withId(R.id.tvOverview)).check(matches(withText(films.results[0].overview)))
    }
/*
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
    }*/
}