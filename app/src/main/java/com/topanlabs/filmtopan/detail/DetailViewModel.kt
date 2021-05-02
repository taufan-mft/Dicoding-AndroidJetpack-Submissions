package com.topanlabs.filmtopan.detail

import androidx.lifecycle.ViewModel
import com.topanlabs.filmtopan.data.*
import com.topanlabs.filmtopan.utils.Resource

/**
 * Created by taufan-mft on 4/19/2021.
 */
class DetailViewModel(val repository: DataRepository) : ViewModel() {

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

    suspend fun getFilmDetail(movieID: Int): Resource<Any> {
        lateinit var resource: Resource<Any>
        try {
            resource = Resource.success(data = repository.getFilmDetail(movieID))
        } catch (exception: Exception) {
            resource = Resource.error(data = null, message = exception.message ?: "Error Occurred!")
        }
        return resource
    }

    suspend fun getTvDetail(tvID: Int): Resource<Any> {
        lateinit var resource: Resource<Any>
        try {
            resource = Resource.success(data = repository.getTvDetail(tvID))
        } catch (exception: Exception) {
            resource = Resource.error(data = null, message = exception.message ?: "Error Occurred!")
        }
        return resource
    }
}