package com.topanlabs.filmtopan.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.RatingData
import com.topanlabs.filmtopan.data.RatingFilmData
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 4/19/2021.
 */
class DetailViewModel(val repository: DataRepository, val espresso: EspressoIdlingResource) : ViewModel() {
    val selectedFilm = MutableLiveData<Resource<Any>>()
    val selectedTv = MutableLiveData<Resource<Any>>()
    fun getFilmDetail(movieID: Int) = liveData(Dispatchers.IO) {
        espresso.increment()

        try {
            emit(Resource.success(data = repository.getFilmDetail(movieID)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
        espresso.decrement()

    }

    fun setFilm(movieID: Int) {
        espresso.increment()
        viewModelScope.launch {
            try {
                selectedFilm.postValue(Resource.success(data = repository.getFilmDetail(movieID)))
            } catch (exception: Exception) {
                selectedFilm.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()

        }
    }

    fun setTv(tvID: Int) {
        espresso.increment()
        viewModelScope.launch {
            try {
                selectedTv.postValue(Resource.success(data = repository.getTvDetail(tvID)))
            } catch (exception: Exception) {
                selectedTv.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()

        }
    }

    suspend fun getFilmRating(movieID: Int): String {
        espresso.increment()
        lateinit var response: RatingFilmData
        var rating = "N/A"
        try {
            response = repository.getFilmRating(movieID)
            for (resp in response.results) {
                if (resp.iso31661 == "US") {
                    rating = resp.releaseDates[0].certification
                }
            }
        } catch (exception: Exception) {
        }
        espresso.decrement()
        return rating
    }

    suspend fun getTvRating(tvID: Int): String {
        espresso.increment()
        lateinit var response: RatingData
        var rating = "N/A"
        try {
            response = repository.getTvRating(tvID)
            rating = response.results[0].rating
        } catch (exception: Exception) {
        }
        espresso.decrement()
        return rating

    }
}