package com.topanlabs.filmtopan.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.data.RatingData
import com.topanlabs.filmtopan.data.RatingFilmData
import com.topanlabs.filmtopan.db.ArtEntity
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import com.topanlabs.filmtopan.utils.Resource
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 4/19/2021.
 */
class DetailViewModel(val repository: DataRepository, val espresso: EspressoIdlingResource) : ViewModel() {
    val selectedFilm = MutableLiveData<Resource<Any>>()
    val selectedTv = MutableLiveData<Resource<Any>>()
    fun allLikedArts(type: String): LiveData<PagedList<ArtEntity>> = repository.allLikedArts(type)

    suspend fun isLiked(id: Int): Boolean {
        val count: Int = repository.searchArt(id)
        return count > 0
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

    fun insert(artEntity: ArtEntity) {
        espresso.increment()
        viewModelScope.launch {
            repository.insert(artEntity)
            espresso.decrement()
        }
    }

    fun delete(artEntity: ArtEntity) {
        espresso.increment()
        viewModelScope.launch {
            repository.delete(artEntity)
            espresso.decrement()
        }
    }
}