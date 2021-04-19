package com.topanlabs.filmtopan.list

import androidx.lifecycle.ViewModel
import com.topanlabs.filmtopan.data.FilmFactory
import com.topanlabs.filmtopan.data.FilmModel
import com.topanlabs.filmtopan.data.TvFactory
import com.topanlabs.filmtopan.data.TvModel

/**
 * Created by taufan-mft on 4/19/2021.
 */
class ListViewModel : ViewModel() {

    fun getFilm(): List<FilmModel> = FilmFactory.listData

    fun getTv(): List<TvModel> = TvFactory.listData
}