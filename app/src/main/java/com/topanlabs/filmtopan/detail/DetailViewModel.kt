package com.topanlabs.filmtopan.detail

import androidx.lifecycle.ViewModel
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.RatingData
import com.topanlabs.filmtopan.data.RatingFilmData
import com.topanlabs.filmtopan.utils.Resource

/**
 * Created by taufan-mft on 4/19/2021.
 */
class DetailViewModel(val repository: DataRepository) : ViewModel() {

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

    suspend fun getFilmRating(movieID: Int): String {
        lateinit var response: RatingFilmData
        var rating = "N/A"
        try {
            response = repository.getFilmRating(movieID)
            for (resp in response.results) {
                if (resp.iso31661.equals("US")) {
                    rating = resp.releaseDates[0].certification
                }
            }
        } catch (exception: Exception) {
        }
        return rating
    }

    suspend fun getTvRating(tvID: Int): String {
        lateinit var response: RatingData
        var rating = "N/A"
        try {
            response = repository.getTvRating(tvID)
            rating = response.results[0].rating
        } catch (exception: Exception) {
        }
        return rating
    }
}