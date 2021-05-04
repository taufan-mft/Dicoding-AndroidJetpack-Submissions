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
import com.topanlabs.filmtopan.data.*
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
    lateinit var detailFilm: FilmDetailData
    lateinit var detailTv: TvDetailData
    lateinit var ratingFilm: RatingFilmData
    lateinit var ratingTv: RatingData

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        runBlocking {
            films = repository.getFilms()
            tvs = repository.getTvs()
            detailFilm = repository.getFilmDetail(films.results[0].id)
            detailTv = repository.getTvDetail(tvs.results[0].id)
            ratingFilm = repository.getFilmRating(films.results[0].id)
            ratingTv = repository.getTvRating(tvs.results[0].id)
            println(ratingTv)

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
        val builder = StringBuilder()
        val iterator = detailFilm.genres.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            if (iterator.hasNext()) {
                builder.append(obj.name)
                builder.append(", ")
            } else {
                builder.append(obj.name)
            }
        }
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(films.results[0].originalTitle)))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(films.results[0].overview)))
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(
            matches(
                withText(
                    films.results[0].releaseDate.substring(
                        0,
                        4
                    )
                )
            )
        )
        onView(withId(R.id.tvTags)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTags)).check(matches(withText(builder.toString())))
        onView(withId(R.id.tvLang)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLang)).check(matches(withText(determineLang(detailFilm.originalLanguage))))
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailFilm.status)))
        onView(withId(R.id.tvAge)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAge)).check(matches(withText(determineFilmRating())))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvScore)).check(
            matches(
                withText(
                    "${
                        (detailFilm.voteAverage * 10).toString().substring(0, 2)
                    }%"
                )
            )
        )
        onView(withId(R.id.lang)).check(matches(isDisplayed()))
        onView(withId(R.id.stat)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.oView)).check(matches(isDisplayed()))
    }

    private fun determineLang(lang: String): String {
        return when (lang) {
            "en" -> "English"
            "es" -> "Espanol"
            "tr" -> "Turkish"
            "ja" -> "Japanese"
            "pl" -> "Polish"
            else -> lang
        }
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
        val builder = StringBuilder()
        val iterator = detailTv.genres.iterator()
        while (iterator.hasNext()) {
            val obj = iterator.next()
            if (iterator.hasNext()) {
                builder.append(obj.name)
                builder.append(", ")
            } else {
                builder.append(obj.name)
            }
        }
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(tvs.results[0].originalName)))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(tvs.results[0].overview)))
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(
            matches(
                withText(
                    tvs.results[0].firstAirDate.substring(
                        0,
                        4
                    )
                )
            )
        )
        onView(withId(R.id.tvTags)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTags)).check(matches(withText(builder.toString())))
        onView(withId(R.id.tvLang)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLang)).check(matches(withText(determineLang(detailTv.originalLanguage))))
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(withText(detailTv.status)))
        onView(withId(R.id.tvAge)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAge)).check(matches(withText(determineTvRating())))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvScore)).check(
            matches(
                withText(
                    "${
                        (detailTv.voteAverage * 10).toString().substring(0, 2)
                    }%"
                )
            )
        )
        onView(withId(R.id.lang)).check(matches(isDisplayed()))
        onView(withId(R.id.stat)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.oView)).check(matches(isDisplayed()))
    }

    private fun determineFilmRating(): String {
        var rating = "N/A"
        val response = ratingFilm
        try {
            for (resp in response.results) {
                if (resp.iso31661 == "US") {
                    rating = resp.releaseDates[0].certification
                }
            }
        } catch (exception: Exception) {
        }
        return rating
    }

    private fun determineTvRating(): String {
        val response = ratingTv
        val rating = response.results[0].rating
        println(rating)
        return rating
    }
}