package com.topanlabs.filmtopan.detail

import androidx.lifecycle.ViewModel
import com.topanlabs.filmtopan.data.FilmFactory
import com.topanlabs.filmtopan.data.FilmModel
import com.topanlabs.filmtopan.data.TvFactory
import com.topanlabs.filmtopan.data.TvModel

/**
 * Created by taufan-mft on 4/19/2021.
 */
class DetailViewModel : ViewModel() {

    fun getFilm(name: String): FilmModel {
        val films = FilmFactory.listData
        lateinit var selectedFilm: FilmModel
        for (film in films) {
            if (film.name == name) {
                selectedFilm = film
                break
            }
        }
        return selectedFilm
    }

    fun getTv(name: String): TvModel {
        val tvs = TvFactory.listData
        lateinit var selectedTv: TvModel
        for (tv in tvs) {
            if (tv.name == name) {
                selectedTv = tv
                break
            }
        }
        return selectedTv
    }
}